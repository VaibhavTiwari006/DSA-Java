import openpyxl
import json
import os
import sys

def sync_from_json(json_file_path=None):
    base_dir = os.path.dirname(os.path.abspath(__file__))
    xlsx_path = os.path.join(base_dir, 'LeetCode_DSA_Tracker.xlsx')
    
    if not json_file_path:
        json_file_path = os.path.join(base_dir, 'web', 'problems.json')

    if not os.path.exists(json_file_path):
        print(f"Error: JSON file not found at {json_file_path}")
        return

    with open(json_file_path, 'r', encoding='utf-8') as f:
        data = json.load(f)

    # Allow passing backup object with { "problems": [...] }
    if isinstance(data, dict) and 'problems' in data:
        problem_list = data['problems']
    elif isinstance(data, list):
        problem_list = data
    else:
        print("Error: Unrecognized JSON format.")
        return

    # Map by problem name (lower) and ID
    problem_map = {}
    for p in problem_list:
        if 'name' in p:
            problem_map[p['name'].strip().lower()] = p

    wb = openpyxl.load_workbook(xlsx_path)
    ws = wb.active

    updated_count = 0
    for row_idx in range(2, ws.max_row + 1):
        cell_name = ws.cell(row=row_idx, column=4).value
        if not cell_name:
            continue
        
        name_key = str(cell_name).strip().lower()
        if name_key in problem_map:
            p_data = problem_map[name_key]
            
            # Solved alone
            if p_data.get('solvedAlone'):
                ws.cell(row=row_idx, column=8, value='YES')
            
            # Date Solved
            if p_data.get('dateSolved'):
                ws.cell(row=row_idx, column=9, value=p_data['dateSolved'])
                
            # Revision 1
            if p_data.get('rev1'):
                ws.cell(row=row_idx, column=10, value=p_data['rev1'])
                
            # Revision 2
            if p_data.get('rev2'):
                ws.cell(row=row_idx, column=11, value=p_data['rev2'])
                
            # Revision 3
            if p_data.get('rev3'):
                ws.cell(row=row_idx, column=12, value=p_data['rev3'])
                
            updated_count += 1

    try:
        wb.save(xlsx_path)
        print(f"Successfully synced {updated_count} problems into {xlsx_path}!")
    except PermissionError:
        print(f"\n[!] Permission Denied: Could not save to {xlsx_path}.")
        print("[!] Please close 'LeetCode_DSA_Tracker.xlsx' in Excel or other programs and try again.")

if __name__ == '__main__':
    json_arg = sys.argv[1] if len(sys.argv) > 1 else None
    sync_from_json(json_arg)
