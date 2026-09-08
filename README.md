# Data Structures and Algorithms in Java

A topic-wise record of Java implementations and LeetCode problem-solving practice.

## Progress Snapshot

| Metric | Current value |
|---|---:|
| LeetCode solution files | 65 |
| Primary language | Java |
| Repository organization | Topic-wise |
| Detailed tracker | [LeetCode_DSA_Tracker.xlsx](LeetCode_DSA_Tracker.xlsx) |

The workbook records the Easy, Medium, and Hard classification for individual problems. The README reports only counts verified directly from the repository; the difficulty totals will be added when they can be calculated automatically from repository metadata.

## Topic Progress

| Topic | Status | Repository |
|---|---|---|
| Java fundamentals | Practised | [Basics_01](Basics_01/) |
| Arrays | Practised | [Arrays_02](Arrays_02/) |
| Strings | Practised | [Strings_03](Strings_03/) |
| Stacks | Practised | [Stack_04](Stack_04/) |
| Queues | Practised | [Queue_05](Queue_05/) |
| Linked lists | Practised | [LinkedList_06](LinkedList_06/) |
| Sorting | Practised | [Sorting_06](Sorting_06/) |
| Recursion and backtracking | Next |
| Trees and binary search trees | Next |
| Heaps | Next |
| Graphs | Next |
| Dynamic programming patterns | In progress |
| Tries and disjoint sets | Planned |

Browse all [LeetCode solutions](Leetcode/).

## Solution Naming Convention

All new LeetCode files use:

```text
P####_ProblemName.java
```

Examples:

```text
P0001_TwoSum.java
P0003_LongestSubstringWithoutRepeatingCharacters.java
P0020_ValidParentheses.java
P0414_ThirdMaximumNumber.java
```

Rules:

1. Use a four-digit, zero-padded problem number.
2. Add one underscore after the problem number.
3. Write the problem title in PascalCase.
4. Avoid spaces and special characters.
5. Keep a public class name consistent with its filename.

Older filenames are retained to avoid unnecessarily changing working source files. New additions follow the standard above.

## Solution Documentation Convention

New solutions should identify the approach and complexity:

```java
// Approach: Dynamic Programming
// Time Complexity: O(n)
// Space Complexity: O(1)
```

When useful, a solution should also include a brief explanation of the state, invariant, or data structure used.

## Monthly Goals

- Prioritize topic mastery over daily problem count.
- Complete a focused set of recursion and backtracking problems.
- Begin tree and binary-search-tree implementations.
- Revisit previously solved problems without referring to earlier code.
- Record the approach and time/space complexity for every new solution.
- Keep filenames and commit messages consistent.

## Run Locally

Compile and run a Java file from the appropriate source root:

```bash
javac path/to/FileName.java
java path.to.FileName
```

Package declarations may require executing the commands from the corresponding package root.

## Author

**Vaibhav Tiwari**

[GitHub](https://github.com/VaibhavTiwari006) · [LinkedIn](https://www.linkedin.com/in/vaibhavtiwari006/)
