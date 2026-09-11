import openpyxl
import json
import os
import re

def export_problems():
    base_dir = os.path.dirname(os.path.abspath(__file__))
    xlsx_path = os.path.join(base_dir, 'LeetCode_DSA_Tracker.xlsx')
    web_dir = os.path.join(base_dir, 'web')
    os.makedirs(web_dir, exist_ok=True)
    
    js_output_path = os.path.join(web_dir, 'problems_data.js')
    json_output_path = os.path.join(web_dir, 'problems.json')

    wb = openpyxl.load_workbook(xlsx_path)
    ws = wb.active

    problems = []

    for row_idx, row in enumerate(ws.iter_rows(min_row=2, values_only=True), start=2):
        if not row[1] or not row[3]: # Topic or Problem missing
            continue

        no = row[0]
        topic = str(row[1]).strip() if row[1] else ''
        subtopic = str(row[2]).strip() if row[2] else ''
        name = str(row[3]).strip() if row[3] else ''
        algo = str(row[4]).strip() if row[4] else '-'
        link = str(row[5]).strip() if row[5] else ''
        diff = str(row[6]).strip() if row[6] else 'Easy'
        solved_alone = str(row[7]).strip() if row[7] else ''
        date_solved = str(row[8]).strip() if row[8] else ''
        rev1 = str(row[9]).strip() if row[9] else ''
        rev2 = str(row[10]).strip() if row[10] else ''
        rev3 = str(row[11]).strip() if row[11] else ''

        # Clean 'None' and 'NA' strings
        def clean_val(v):
            if v in ['None', 'NA', '-', 'null', None]:
                return ''
            return v

        solved_alone = 'YES' if clean_val(solved_alone) == 'YES' else ''
        date_solved = clean_val(date_solved)
        rev1 = clean_val(rev1)
        rev2 = clean_val(rev2)
        rev3 = clean_val(rev3)

        # Generate unique problem slug/id
        slug = re.sub(r'[^a-zA-Z0-9]+', '-', name.lower()).strip('-')

        problems.append({
            'id': f'p_{no}_{slug}',
            'no': no,
            'topic': topic,
            'subtopic': subtopic,
            'name': name,
            'algo': algo,
            'link': link,
            'diff': diff,
            'solvedAlone': solved_alone == 'YES',
            'dateSolved': date_solved,
            'rev1': rev1,
            'rev2': rev2,
            'rev3': rev3,
            'notes': ''
        })

    # Save to JSON
    with open(json_output_path, 'w', encoding='utf-8') as f:
        json.dump(problems, f, indent=2, ensure_ascii=False)

    # Save to JS for instant loading without CORS/fetch restrictions
    js_content = f"// Automatically generated from LeetCode_DSA_Tracker.xlsx\nwindow.INITIAL_PROBLEMS = {json.dumps(problems, indent=2, ensure_ascii=False)};\n"
    with open(js_output_path, 'w', encoding='utf-8') as f:
        f.write(js_content)

    print(f"Successfully exported {len(problems)} problems to {js_output_path} and {json_output_path}")

if __name__ == '__main__':
    export_problems()
