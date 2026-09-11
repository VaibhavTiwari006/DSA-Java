// DSA Spaced Repetition Revision Web App Engine
// Author: Vaibhav Tiwari

(function () {
  'use strict';

  const STORAGE_KEY = 'LEETCODE_DSA_REVISION_V3';
  const SETTINGS_KEY = 'LEETCODE_DSA_SETTINGS_V3';

  // State
  let problems = [];
  let simulatedDate = new Date();
  simulatedDate.setHours(0, 0, 0, 0);

  let activeTab = 'today';
  let filters = {
    search: '',
    topic: 'ALL',
    difficulty: 'ALL',
    status: 'ALL'
  };

  // Helper: Parse date strings (DD-MM-YYYY or YYYY-MM-DD)
  function parseDate(str) {
    if (!str || typeof str !== 'string') return null;
    str = str.trim();
    if (!str) return null;

    if (str.includes('-')) {
      const parts = str.split('-');
      if (parts[0].length === 4) {
        // YYYY-MM-DD
        return new Date(parseInt(parts[0]), parseInt(parts[1]) - 1, parseInt(parts[2]));
      } else if (parts[2].length === 4) {
        // DD-MM-YYYY
        return new Date(parseInt(parts[2]), parseInt(parts[1]) - 1, parseInt(parts[0]));
      }
    }
    const d = new Date(str);
    return isNaN(d.getTime()) ? null : d;
  }

  // Helper: Format date to DD-MM-YYYY
  function formatDate(d) {
    if (!d) return '';
    const day = String(d.getDate()).padStart(2, '0');
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const year = d.getFullYear();
    return `${day}-${month}-${year}`;
  }

  function addDays(date, days) {
    const result = new Date(date);
    result.setDate(result.getDate() + days);
    result.setHours(0, 0, 0, 0);
    return result;
  }

  function daysBetween(d1, d2) {
    const diff = d2.getTime() - d1.getTime();
    return Math.round(diff / (1000 * 60 * 60 * 24));
  }

  // Calculate spaced repetition status for a problem
  function calculateRevisionStatus(p) {
    const isSolved = p.solvedAlone || !!p.dateSolved;
    if (!isSolved) {
      return {
        status: 'UNSOLVED',
        nextStage: null,
        targetDueDate: null,
        daysDiff: 0,
        label: 'Not Solved Yet'
      };
    }

    function isVal(v) {
      return !!v && v !== 'NA' && v !== '-' && v !== 'None' && v !== 'null';
    }

    const hasRev1 = isVal(p.rev1);
    const hasRev2 = isVal(p.rev2);
    const hasRev3 = isVal(p.rev3);

    if (hasRev3) {
      return {
        status: 'MASTERED',
        nextStage: null,
        targetDueDate: null,
        daysDiff: 0,
        label: 'Mastered 🏆'
      };
    }

    let baseDate = null;
    let nextStage = '';
    let intervalDays = 0;

    if (hasRev2 && !hasRev3) {
      nextStage = 'Rev 3';
      baseDate = parseDate(p.rev2) || parseDate(p.dateSolved);
      intervalDays = 21; // 3 weeks after Rev 2
    } else if (hasRev1 && !hasRev2) {
      nextStage = 'Rev 2';
      baseDate = parseDate(p.rev1) || parseDate(p.dateSolved);
      intervalDays = 7; // 1 week after Rev 1
    } else {
      nextStage = 'Rev 1';
      baseDate = parseDate(p.dateSolved);
      intervalDays = 1; // 1 day after solving
    }

    if (!baseDate) {
      baseDate = simulatedDate;
    }

    const targetDueDate = addDays(baseDate, intervalDays);
    const daysDiff = daysBetween(targetDueDate, simulatedDate); // positive = overdue, 0 = today, negative = upcoming

    if (daysDiff > 0) {
      return {
        status: 'OVERDUE',
        nextStage,
        targetDueDate,
        daysDiff,
        label: `Overdue by ${daysDiff}d (${nextStage})`
      };
    } else if (daysDiff === 0) {
      return {
        status: 'DUE_TODAY',
        nextStage,
        targetDueDate,
        daysDiff: 0,
        label: `Due Today (${nextStage})`
      };
    } else {
      return {
        status: 'UPCOMING',
        nextStage,
        targetDueDate,
        daysDiff: Math.abs(daysDiff),
        label: `In ${Math.abs(daysDiff)}d (${nextStage})`
      };
    }
  }

  // Load state
  function loadState() {
    try {
      const saved = localStorage.getItem(STORAGE_KEY);
      if (saved) {
        problems = JSON.parse(saved);
        return;
      }
    } catch (e) {
      console.warn('Failed to parse localStorage:', e);
    }

    // Default to INITIAL_PROBLEMS from problems_data.js
    if (window.INITIAL_PROBLEMS && Array.isArray(window.INITIAL_PROBLEMS)) {
      problems = JSON.parse(JSON.stringify(window.INITIAL_PROBLEMS));
    } else {
      problems = [];
    }
    saveState();
  }

  function saveState() {
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(problems));
    } catch (e) {
      console.error('Failed to save to localStorage:', e);
    }
  }

  // Actions
  function markRevision(id, stage) {
    const p = problems.find(item => item.id === id);
    if (!p) return;

    const todayStr = formatDate(simulatedDate);
    if (stage === 'Rev 1') {
      p.rev1 = todayStr;
      showToast(`Marked Rev 1 complete for "${p.name}"! Next revision in 7 days.`);
    } else if (stage === 'Rev 2') {
      p.rev2 = todayStr;
      showToast(`Marked Rev 2 complete for "${p.name}"! Next revision in 21 days.`);
    } else if (stage === 'Rev 3') {
      p.rev3 = todayStr;
      showToast(`🏆 Problem "${p.name}" Mastered! All 3 spaced revisions completed!`);
    }

    saveState();
    renderApp();
  }

  function undoRevision(id) {
    const p = problems.find(item => item.id === id);
    if (!p) return;

    if (p.rev3) {
      p.rev3 = '';
      showToast(`↩ Undid Rev 3 for "${p.name}". Reverted to Rev 2.`);
    } else if (p.rev2) {
      p.rev2 = '';
      showToast(`↩ Undid Rev 2 for "${p.name}". Reverted to Rev 1.`);
    } else if (p.rev1) {
      p.rev1 = '';
      showToast(`↩ Undid Rev 1 for "${p.name}". Fully reset revision.`);
    } else {
      showToast(`No revisions to undo for "${p.name}".`);
    }

    saveState();
    renderApp();
  }

  function resetProblemRevisions(id) {
    const p = problems.find(item => item.id === id);
    if (!p) return;
    p.rev1 = '';
    p.rev2 = '';
    p.rev3 = '';
    saveState();
    showToast(`🔄 Reset all revisions for "${p.name}".`);
    renderApp();
  }

  function toggleSolved(id) {
    const p = problems.find(item => item.id === id);
    if (!p) return;

    p.solvedAlone = !p.solvedAlone;
    if (p.solvedAlone && !p.dateSolved) {
      p.dateSolved = formatDate(simulatedDate);
    }
    saveState();
    renderApp();
  }

  function updateNotes(id, text) {
    const p = problems.find(item => item.id === id);
    if (!p) return;
    p.notes = text;
    saveState();
  }

  function postponeProblem(id, days) {
    const p = problems.find(item => item.id === id);
    if (!p) return;

    // Postpone shifts target date by temporarily pushing base date or rev date
    const revInfo = calculateRevisionStatus(p);
    if (revInfo.nextStage === 'Rev 1') {
      p.dateSolved = formatDate(addDays(parseDate(p.dateSolved) || simulatedDate, days));
    } else if (revInfo.nextStage === 'Rev 2') {
      p.rev1 = formatDate(addDays(parseDate(p.rev1) || simulatedDate, days));
    } else if (revInfo.nextStage === 'Rev 3') {
      p.rev2 = formatDate(addDays(parseDate(p.rev2) || simulatedDate, days));
    }

    saveState();
    showToast(`Postponed "${p.name}" revision by ${days} day(s).`);
    renderApp();
  }

  function resetToDefault() {
    if (confirm('Are you sure you want to reset all data back to the original Excel spreadsheet? Any manual edits will be lost.')) {
      if (window.INITIAL_PROBLEMS) {
        problems = JSON.parse(JSON.stringify(window.INITIAL_PROBLEMS));
        saveState();
        showToast('Reset data to Excel initial snapshot.');
        renderApp();
      }
    }
  }

  // Toast Notification
  function showToast(msg) {
    let container = document.getElementById('toast-container');
    if (!container) {
      container = document.createElement('div');
      container.id = 'toast-container';
      container.className = 'toast-container';
      document.body.appendChild(container);
    }

    const toast = document.createElement('div');
    toast.className = 'toast';
    toast.textContent = msg;
    container.appendChild(toast);

    setTimeout(() => {
      toast.style.opacity = '0';
      toast.style.transition = 'opacity 0.3s ease';
      setTimeout(() => toast.remove(), 300);
    }, 3500);
  }

  // UI Rendering
  function renderStats() {
    let dueTodayCount = 0;
    let overdueCount = 0;
    let upcomingCount = 0;
    let masteredCount = 0;
    let totalSolved = 0;

    problems.forEach(p => {
      const isSolved = p.solvedAlone || !!p.dateSolved;
      if (isSolved) totalSolved++;

      const info = calculateRevisionStatus(p);
      if (info.status === 'DUE_TODAY') dueTodayCount++;
      else if (info.status === 'OVERDUE') overdueCount++;
      else if (info.status === 'UPCOMING') upcomingCount++;
      else if (info.status === 'MASTERED') masteredCount++;
    });

    document.getElementById('stat-due-today').textContent = dueTodayCount;
    document.getElementById('stat-overdue').textContent = overdueCount;
    document.getElementById('stat-upcoming').textContent = upcomingCount;
    document.getElementById('stat-mastered').textContent = masteredCount;
    document.getElementById('stat-total-solved').textContent = `${totalSolved} / ${problems.length}`;

    // Update banner badge
    const bannerBadge = document.getElementById('current-date-display');
    if (bannerBadge) {
      const options = { weekday: 'short', month: 'short', day: 'numeric', year: 'numeric' };
      bannerBadge.textContent = simulatedDate.toLocaleDateString('en-US', options);
    }
  }

  function renderTodayQueue() {
    const container = document.getElementById('today-queue-container');
    if (!container) return;

    // Filter problems that are OVERDUE or DUE_TODAY
    const items = problems.map(p => ({
      problem: p,
      revInfo: calculateRevisionStatus(p)
    })).filter(item => item.revInfo.status === 'OVERDUE' || item.revInfo.status === 'DUE_TODAY');

    // Sort overdue first (longest overdue top), then due today
    items.sort((a, b) => {
      if (a.revInfo.status === 'OVERDUE' && b.revInfo.status !== 'OVERDUE') return -1;
      if (b.revInfo.status === 'OVERDUE' && a.revInfo.status !== 'OVERDUE') return 1;
      return b.revInfo.daysDiff - a.revInfo.daysDiff;
    });

    const badge = document.getElementById('today-count-badge');
    if (badge) badge.textContent = items.length;

    if (items.length === 0) {
      container.innerHTML = `
        <div class="empty-state">
          <div class="empty-icon">🎉</div>
          <div class="empty-title">All Caught Up for Today!</div>
          <p>No questions are currently due for revision. Check the <strong>Upcoming</strong> tab or solve new problems!</p>
        </div>
      `;
      return;
    }

    container.innerHTML = items.map(item => createProblemCard(item.problem, item.revInfo)).join('');
    bindCardEvents(container);
  }

  function renderUpcoming() {
    const container = document.getElementById('upcoming-container');
    if (!container) return;

    const items = problems.map(p => ({
      problem: p,
      revInfo: calculateRevisionStatus(p)
    })).filter(item => item.revInfo.status === 'UPCOMING');

    // Sort by soonest due
    items.sort((a, b) => a.revInfo.daysDiff - b.revInfo.daysDiff);

    const badge = document.getElementById('upcoming-count-badge');
    if (badge) badge.textContent = items.length;

    if (items.length === 0) {
      container.innerHTML = `
        <div class="empty-state">
          <div class="empty-icon">🌱</div>
          <div class="empty-title">No Upcoming Revisions Scheduled</div>
          <p>Solve questions and mark revisions to schedule future spaced repetition dates.</p>
        </div>
      `;
      return;
    }

    container.innerHTML = items.map(item => createProblemCard(item.problem, item.revInfo)).join('');
    bindCardEvents(container);
  }

  function renderAllTable() {
    const tbody = document.getElementById('problems-tbody');
    if (!tbody) return;

    // Apply filters
    const filtered = problems.filter(p => {
      const revInfo = calculateRevisionStatus(p);

      // Search filter
      if (filters.search) {
        const query = filters.search.toLowerCase();
        const matchName = p.name.toLowerCase().includes(query);
        const matchTopic = p.topic.toLowerCase().includes(query);
        const matchSubtopic = p.subtopic.toLowerCase().includes(query);
        const matchAlgo = p.algo.toLowerCase().includes(query);
        if (!matchName && !matchTopic && !matchSubtopic && !matchAlgo) return false;
      }

      // Topic filter
      if (filters.topic !== 'ALL' && p.topic !== filters.topic) {
        return false;
      }

      // Difficulty filter
      if (filters.difficulty !== 'ALL' && p.diff !== filters.difficulty) {
        return false;
      }

      // Status filter
      if (filters.status !== 'ALL') {
        if (filters.status === 'MASTERED' && revInfo.status !== 'MASTERED') return false;
        if (filters.status === 'DUE' && revInfo.status !== 'DUE_TODAY' && revInfo.status !== 'OVERDUE') return false;
        if (filters.status === 'UNSOLVED' && revInfo.status !== 'UNSOLVED') return false;
        if (filters.status === 'UPCOMING' && revInfo.status !== 'UPCOMING') return false;
      }

      return true;
    });

    const badge = document.getElementById('all-count-badge');
    if (badge) badge.textContent = `${filtered.length} / ${problems.length}`;

    if (filtered.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="8" style="text-align: center; padding: 2.5rem; color: var(--text-muted);">
            No problems match the current search or filters.
          </td>
        </tr>
      `;
      return;
    }

    tbody.innerHTML = filtered.map(p => {
      const revInfo = calculateRevisionStatus(p);
      const diffClass = p.diff === 'Easy' ? 'tag-easy' : (p.diff === 'Medium' ? 'tag-medium' : 'tag-hard');

      return `
        <tr>
          <td style="font-family: var(--font-mono); color: var(--text-muted);">${p.no}</td>
          <td>
            <strong>${escapeHtml(p.topic)}</strong>
            <div style="font-size: 0.78rem; color: var(--text-muted);">${escapeHtml(p.subtopic)}</div>
          </td>
          <td>
            <a href="${p.link || '#'}" target="_blank" style="color: var(--text-primary); font-weight: 600; text-decoration: none;">
              ${escapeHtml(p.name)} ↗
            </a>
          </td>
          <td><span class="tag ${diffClass}">${p.diff}</span></td>
          <td><span style="font-family: var(--font-mono); font-size: 0.8rem; color: var(--accent-orange-bright);">${escapeHtml(p.algo)}</span></td>
          <td>
            <span class="tag ${getStatusTagClass(revInfo.status)}">
              ${revInfo.label}
            </span>
          </td>
          <td>
            <div style="font-size: 0.78rem; font-family: var(--font-mono);">
              <span style="color: ${p.rev1 ? 'var(--accent-green-bright)' : 'var(--text-muted)'}">R1: ${p.rev1 || '-'}</span> | 
              <span style="color: ${p.rev2 ? 'var(--accent-green-bright)' : 'var(--text-muted)'}">R2: ${p.rev2 || '-'}</span> | 
              <span style="color: ${p.rev3 ? 'var(--accent-green-bright)' : 'var(--text-muted)'}">R3: ${p.rev3 || '-'}</span>
            </div>
          </td>
          <td>
            <div style="display: flex; gap: 0.35rem; align-items: center; flex-wrap: wrap;">
              ${revInfo.nextStage ? `
                <button class="btn btn-primary btn-sm mark-rev-btn" data-id="${p.id}" data-stage="${revInfo.nextStage}">
                  ✓ Mark ${revInfo.nextStage}
                </button>
              ` : (revInfo.status === 'MASTERED' ? '<span style="color: var(--accent-green-bright); font-size: 0.85rem; font-weight: 600;">Mastered 🏆</span>' : '')}
              ${(p.rev1 || p.rev2 || p.rev3) ? `
                <button class="btn btn-sm undo-rev-btn" data-id="${p.id}" title="Undo last revision" style="color: var(--accent-red-bright); padding: 0.25rem 0.55rem; font-size: 0.78rem;">
                  ↩ Undo
                </button>
                <button class="btn btn-sm reset-rev-btn" data-id="${p.id}" title="Reset all revisions back to unrevised" style="color: var(--text-muted); padding: 0.25rem 0.45rem; font-size: 0.78rem;">
                  ✕ Reset
                </button>
              ` : ''}
            </div>
          </td>
        </tr>
      `;
    }).join('');

    // Bind row action buttons
    tbody.querySelectorAll('.mark-rev-btn').forEach(btn => {
      btn.addEventListener('click', () => {
        markRevision(btn.dataset.id, btn.dataset.stage);
      });
    });

    tbody.querySelectorAll('.undo-rev-btn').forEach(btn => {
      btn.addEventListener('click', () => {
        undoRevision(btn.dataset.id);
      });
    });

    tbody.querySelectorAll('.reset-rev-btn').forEach(btn => {
      btn.addEventListener('click', () => {
        if (confirm('Reset all revisions for this problem?')) {
          resetProblemRevisions(btn.dataset.id);
        }
      });
    });
  }

  function createProblemCard(p, revInfo) {
    const diffClass = p.diff === 'Easy' ? 'tag-easy' : (p.diff === 'Medium' ? 'tag-medium' : 'tag-hard');
    const cardStatusClass = revInfo.status === 'OVERDUE' ? 'card-overdue'
      : (revInfo.status === 'DUE_TODAY' ? 'card-due-today'
      : (revInfo.status === 'MASTERED' ? 'card-mastered' : 'card-upcoming'));

    return `
      <div class="problem-card ${cardStatusClass}" data-id="${p.id}">
        <div class="card-top">
          <div>
            <div class="problem-meta">
              <span class="tag ${diffClass}">${p.diff}</span>
              <span class="tag tag-topic">${escapeHtml(p.topic)}</span>
              <span class="tag ${getStatusTagClass(revInfo.status)}">${revInfo.label}</span>
            </div>
            <h3 class="problem-title">
              <a href="${p.link || '#'}" target="_blank" rel="noopener noreferrer">
                ${p.no}. ${escapeHtml(p.name)} ↗
              </a>
            </h3>
            <div class="subtopic-text">${escapeHtml(p.subtopic)}</div>
          </div>
        </div>

        <!-- Active Recall / Spoiler Box -->
        <div class="recall-box">
          <div class="recall-trigger" data-action="toggle-recall">
            <span>💡 Approach & Key Insight</span>
            <span class="recall-icon">▶ Reveal</span>
          </div>
          <div class="recall-content">
            <div style="margin-bottom: 0.25rem;"><strong>Algorithm:</strong> <span class="recall-algo">${escapeHtml(p.algo)}</span></div>
            <div><strong>Core Concept:</strong> ${escapeHtml(p.subtopic)}</div>
            ${p.notes ? `<div style="margin-top: 0.35rem; font-style: italic; color: var(--text-secondary);">"${escapeHtml(p.notes)}"</div>` : ''}
          </div>
        </div>

        <!-- Revision Progress Indicators -->
        <div class="revision-stages">
          <div class="stage-pill ${p.rev1 ? 'done' : (revInfo.nextStage === 'Rev 1' ? 'current' : '')}">
            ${p.rev1 ? `✓ Rev 1 (${p.rev1})` : 'Rev 1 (+1d)'}
          </div>
          <div class="stage-pill ${p.rev2 ? 'done' : (revInfo.nextStage === 'Rev 2' ? 'current' : '')}">
            ${p.rev2 ? `✓ Rev 2 (${p.rev2})` : 'Rev 2 (+7d)'}
          </div>
          <div class="stage-pill ${p.rev3 ? 'done' : (revInfo.nextStage === 'Rev 3' ? 'current' : '')}">
            ${p.rev3 ? `✓ Rev 3 (${p.rev3})` : 'Rev 3 (+21d)'}
          </div>
        </div>

        <!-- Actions -->
        <div class="card-actions">
          ${revInfo.nextStage ? `
            <button class="btn btn-success btn-sm action-mark-rev" data-stage="${revInfo.nextStage}">
              ✓ Mark ${revInfo.nextStage} Done
            </button>
            <button class="btn btn-sm action-postpone" data-days="1" title="Postpone by 1 day">
              +1 Day
            </button>
          ` : '<span style="color: var(--accent-green-bright); font-weight: 600; font-size: 0.85rem;">Mastered 🏆</span>'}
          ${(p.rev1 || p.rev2 || p.rev3) ? `
            <button class="btn btn-sm action-undo" title="Undo last revision" style="color: var(--accent-red-bright);">
              ↩ Undo
            </button>
          ` : ''}
          <button class="btn btn-sm action-notes" title="Add/Edit Personal Notes">
            📝 Note
          </button>
          <a href="${p.link || '#'}" target="_blank" class="btn btn-sm" style="margin-left: auto;">
            Solve ↗
          </a>
        </div>
      </div>
    `;
  }

  function bindCardEvents(container) {
    container.querySelectorAll('.problem-card').forEach(card => {
      const id = card.dataset.id;

      // Toggle recall
      const recallTrigger = card.querySelector('[data-action="toggle-recall"]');
      const recallContent = card.querySelector('.recall-content');
      const recallIcon = card.querySelector('.recall-icon');
      if (recallTrigger && recallContent) {
        recallTrigger.addEventListener('click', () => {
          const isRevealed = recallContent.classList.toggle('revealed');
          recallIcon.textContent = isRevealed ? '▼ Hide' : '▶ Reveal';
        });
      }

      // Mark revision
      const markBtn = card.querySelector('.action-mark-rev');
      if (markBtn) {
        markBtn.addEventListener('click', () => {
          markRevision(id, markBtn.dataset.stage);
        });
      }

      // Undo revision
      const undoBtn = card.querySelector('.action-undo');
      if (undoBtn) {
        undoBtn.addEventListener('click', () => {
          undoRevision(id);
        });
      }

      // Postpone
      const postponeBtn = card.querySelector('.action-postpone');
      if (postponeBtn) {
        postponeBtn.addEventListener('click', () => {
          postponeProblem(id, parseInt(postponeBtn.dataset.days || '1'));
        });
      }

      // Note modal
      const noteBtn = card.querySelector('.action-notes');
      if (noteBtn) {
        noteBtn.addEventListener('click', () => {
          openNotesModal(id);
        });
      }
    });
  }

  function getStatusTagClass(status) {
    switch (status) {
      case 'OVERDUE': return 'tag-hard';
      case 'DUE_TODAY': return 'tag-stage';
      case 'UPCOMING': return 'tag-medium';
      case 'MASTERED': return 'tag-easy';
      default: return 'tag-topic';
    }
  }

  function escapeHtml(str) {
    if (!str) return '';
    return String(str)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;');
  }

  // Populate Topic Filter Dropdown
  function populateTopicFilter() {
    const select = document.getElementById('filter-topic');
    if (!select) return;

    const topics = Array.from(new Set(problems.map(p => p.topic).filter(Boolean))).sort();
    const currentVal = select.value;
    select.innerHTML = '<option value="ALL">All Topics</option>' +
      topics.map(t => `<option value="${escapeHtml(t)}">${escapeHtml(t)}</option>`).join('');
    select.value = currentVal;
  }

  // Analytics View
  function renderAnalytics() {
    const container = document.getElementById('analytics-container');
    if (!container) return;

    const topics = Array.from(new Set(problems.map(p => p.topic).filter(Boolean)));
    const topicStats = topics.map(topic => {
      const topicProblems = problems.filter(p => p.topic === topic);
      const solved = topicProblems.filter(p => p.solvedAlone || !!p.dateSolved).length;
      const mastered = topicProblems.filter(p => calculateRevisionStatus(p).status === 'MASTERED').length;
      return {
        topic,
        total: topicProblems.length,
        solved,
        mastered,
        pct: Math.round((solved / topicProblems.length) * 100)
      };
    });

    topicStats.sort((a, b) => b.total - a.total);

    container.innerHTML = `
      <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 1.5rem;">
        ${topicStats.map(s => `
          <div class="stat-card">
            <div class="stat-header">
              <span class="stat-title">${escapeHtml(s.topic)}</span>
              <span style="font-weight: 700; color: var(--accent-blue-hover);">${s.pct}%</span>
            </div>
            <div style="background: var(--bg-tertiary); height: 8px; border-radius: 4px; overflow: hidden; margin-bottom: 0.75rem;">
              <div style="background: var(--accent-blue); width: ${s.pct}%; height: 100%;"></div>
            </div>
            <div style="display: flex; justify-content: space-between; font-size: 0.8rem; color: var(--text-secondary);">
              <span>Solved: ${s.solved} / ${s.total}</span>
              <span>Mastered: ${s.mastered}</span>
            </div>
          </div>
        `).join('')}
      </div>
    `;
  }

  // Notes Modal
  function openNotesModal(id) {
    const p = problems.find(item => item.id === id);
    if (!p) return;

    const modal = document.getElementById('notes-modal');
    const title = document.getElementById('notes-modal-title');
    const textarea = document.getElementById('notes-modal-textarea');
    const saveBtn = document.getElementById('notes-modal-save');

    if (!modal || !textarea) return;

    title.textContent = `Notes: ${p.name}`;
    textarea.value = p.notes || '';
    modal.classList.add('open');

    saveBtn.onclick = () => {
      updateNotes(id, textarea.value.trim());
      modal.classList.remove('open');
      showToast('Notes saved successfully.');
      renderApp();
    };
  }

  // JSON Import & Export
  function exportDataJSON() {
    // Also build a progress object compatible with the user's sample JSON format:
    // { "version": 3, "exportedAt": "...", "progress": { ... } }
    const progressMap = {};
    problems.forEach(p => {
      const isMastered = calculateRevisionStatus(p).status === 'MASTERED';
      progressMap[p.id] = isMastered;
    });

    const exportObj = {
      version: 3,
      exportedAt: new Date().toISOString(),
      revisionTracker: {
        currentDate: formatDate(simulatedDate),
        totalCount: problems.length,
        masteredCount: problems.filter(p => calculateRevisionStatus(p).status === 'MASTERED').length
      },
      progress: progressMap,
      problems: problems
    };

    const dataStr = 'data:text/json;charset=utf-8,' + encodeURIComponent(JSON.stringify(exportObj, null, 2));
    const dlAnchor = document.createElement('a');
    dlAnchor.setAttribute('href', dataStr);
    dlAnchor.setAttribute('download', `dsa_revision_backup_${formatDate(simulatedDate)}.json`);
    dlAnchor.click();
    showToast('Exported revision JSON file!');
  }

  function openImportModal() {
    const modal = document.getElementById('import-modal');
    if (modal) modal.classList.add('open');
  }

  function handleImportSubmit() {
    const textarea = document.getElementById('import-textarea');
    if (!textarea) return;
    try {
      const parsed = JSON.parse(textarea.value.trim());
      if (parsed.problems && Array.isArray(parsed.problems)) {
        problems = parsed.problems;
        saveState();
        showToast('Successfully imported full DSA problem revision state!');
      } else if (parsed.progress && typeof parsed.progress === 'object') {
        // Sample JSON format matching user's input: { progress: { key: boolean } }
        let matched = 0;
        for (const [key, val] of Object.entries(parsed.progress)) {
          const match = problems.find(p => p.id === key || p.name.toLowerCase().includes(key.toLowerCase()));
          if (match && val === true) {
            match.solvedAlone = true;
            if (!match.rev1) match.rev1 = formatDate(simulatedDate);
            matched++;
          }
        }
        saveState();
        showToast(`Imported progress JSON: marked ${matched} items complete!`);
      } else {
        alert('Unrecognized JSON structure. Please check your file.');
        return;
      }
      document.getElementById('import-modal').classList.remove('open');
      renderApp();
    } catch (e) {
      alert('Invalid JSON: ' + e.message);
    }
  }

  // Date Simulation Controls
  function adjustDate(days) {
    simulatedDate = addDays(simulatedDate, days);
    showToast(`Simulated date moved to ${formatDate(simulatedDate)}`);
    renderApp();
  }

  function resetDateToToday() {
    simulatedDate = new Date();
    simulatedDate.setHours(0, 0, 0, 0);
    showToast('Reset date to system today.');
    renderApp();
  }

  // Master Render
  function renderApp() {
    renderStats();
    if (activeTab === 'today') {
      renderTodayQueue();
    } else if (activeTab === 'upcoming') {
      renderUpcoming();
    } else if (activeTab === 'all') {
      renderAllTable();
    } else if (activeTab === 'analytics') {
      renderAnalytics();
    }
  }

  // Setup Event Listeners
  function setupEvents() {
    // Tabs
    document.querySelectorAll('.nav-btn[data-tab]').forEach(btn => {
      btn.addEventListener('click', () => {
        document.querySelectorAll('.nav-btn[data-tab]').forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        activeTab = btn.dataset.tab;

        document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));
        const target = document.getElementById(`tab-${activeTab}`);
        if (target) target.classList.add('active');

        renderApp();
      });
    });

    // Filters
    const searchInput = document.getElementById('search-input');
    if (searchInput) {
      searchInput.addEventListener('input', (e) => {
        filters.search = e.target.value;
        renderAllTable();
      });
    }

    const topicFilter = document.getElementById('filter-topic');
    if (topicFilter) {
      topicFilter.addEventListener('change', (e) => {
        filters.topic = e.target.value;
        renderAllTable();
      });
    }

    const diffFilter = document.getElementById('filter-difficulty');
    if (diffFilter) {
      diffFilter.addEventListener('change', (e) => {
        filters.difficulty = e.target.value;
        renderAllTable();
      });
    }

    const statusFilter = document.getElementById('filter-status');
    if (statusFilter) {
      statusFilter.addEventListener('change', (e) => {
        filters.status = e.target.value;
        renderAllTable();
      });
    }

    // Modal Closes
    document.querySelectorAll('.modal-close, .modal-overlay').forEach(el => {
      el.addEventListener('click', (e) => {
        if (e.target === el) {
          document.querySelectorAll('.modal-overlay').forEach(m => m.classList.remove('open'));
        }
      });
    });

    // Header buttons
    const exportBtn = document.getElementById('btn-export-json');
    if (exportBtn) exportBtn.addEventListener('click', exportDataJSON);

    const importBtn = document.getElementById('btn-import-json');
    if (importBtn) importBtn.addEventListener('click', openImportModal);

    const submitImportBtn = document.getElementById('btn-submit-import');
    if (submitImportBtn) submitImportBtn.addEventListener('click', handleImportSubmit);

    const resetBtn = document.getElementById('btn-reset-data');
    if (resetBtn) resetBtn.addEventListener('click', resetToDefault);

    // Date simulation buttons
    const nextDayBtn = document.getElementById('btn-next-day');
    if (nextDayBtn) nextDayBtn.addEventListener('click', () => adjustDate(1));

    const nextWeekBtn = document.getElementById('btn-next-week');
    if (nextWeekBtn) nextWeekBtn.addEventListener('click', () => adjustDate(7));

    const resetDateBtn = document.getElementById('btn-reset-date');
    if (resetDateBtn) resetDateBtn.addEventListener('click', resetDateToToday);
  }

  // Initialization
  function init() {
    loadState();
    setupEvents();
    populateTopicFilter();
    renderApp();
  }

  document.addEventListener('DOMContentLoaded', init);
})();
