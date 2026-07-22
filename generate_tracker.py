import sys
import subprocess

def install(package):
    subprocess.check_call([sys.executable, "-m", "pip", "install", package])

try:
    from openpyxl import Workbook
    from openpyxl.styles import Font, PatternFill, Alignment, Border, Side
except ImportError:
    install('openpyxl')
    from openpyxl import Workbook
    from openpyxl.styles import Font, PatternFill, Alignment, Border, Side

def generate_tracker():
    xlsx_file = r'c:\Users\Vaibhav\DSA-Java\LeetCode_DSA_Tracker.xlsx'

    data = [
        ["No.", "Topic", "Subtopic", "Problem", "LeetCode Link", "Difficulty", "Similar Pepcoding Topic", "Solved Alone?", "Time Taken (mins)", "Date Solved", "Revision 1 (1 day)", "Revision 2 (1 week)", "Revision 3 (1 month)"],
        
        # Basics & Math
        [1, "Basics", "Getting Started", "Fibonacci Number", "https://leetcode.com/problems/fibonacci-number/", "Easy", "Fibonacci Numbers", "", "", "", "", "", ""],
        [2, "Basics", "Getting Started", "Climbing Stairs", "https://leetcode.com/problems/climbing-stairs/", "Easy", "Fibonacci Numbers", "", "", "", "", "", ""],
        [3, "Basics", "Math", "Missing Number", "https://leetcode.com/problems/missing-number/", "Easy", "Math/Loops", "", "", "", "", "", ""],
        [4, "Basics", "Math", "Palindrome Number", "https://leetcode.com/problems/palindrome-number/", "Easy", "Reverse a Number", "", "", "", "", "", ""],
        [5, "Basics", "Math", "Reverse Integer", "https://leetcode.com/problems/reverse-integer/", "Medium", "Reverse a Number", "", "", "", "", "", ""],
        [6, "Basics", "Math", "Count Primes", "https://leetcode.com/problems/count-primes/", "Medium", "Prime Numbers", "", "", "", "", "", ""],
        
        # Number System
        [7, "Number System", "Base Conversion", "Base 7", "https://leetcode.com/problems/base-7/", "Easy", "Decimal to Any Base", "", "", "", "", "", ""],
        [8, "Number System", "Addition", "Add Binary", "https://leetcode.com/problems/add-binary/", "Easy", "Any Base Addition", "", "", "", "", "", ""],
        [9, "Number System", "Addition", "Add to Array-Form of Integer", "https://leetcode.com/problems/add-to-array-form-of-integer/", "Easy", "Sum of two arrays", "", "", "", "", "", ""],
        
        # Arrays
        [10, "Arrays", "Search/Math", "Two Sum", "https://leetcode.com/problems/two-sum/", "Easy", "Find Element in Array", "", "", "", "", "", ""],
        [11, "Arrays", "Search/Math", "Find Target Indices After Sorting Array", "https://leetcode.com/problems/find-target-indices-after-sorting-array/", "Easy", "Find Element in Array", "", "", "", "", "", ""],
        [12, "Arrays", "Two Pointers", "Move Zeroes", "https://leetcode.com/problems/move-zeroes/", "Easy", "Array Manipulation", "", "", "", "", "", ""],
        [13, "Arrays", "Two Pointers", "Squares of a Sorted Array", "https://leetcode.com/problems/squares-of-a-sorted-array/", "Easy", "Array Manipulation", "", "", "", "", "", ""],
        [14, "Arrays", "Counting", "Majority Element", "https://leetcode.com/problems/majority-element/", "Easy", "Array Manipulation", "", "", "", "", "", ""],
        [15, "Arrays", "Subarrays", "Best Time to Buy and Sell Stock", "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/", "Easy", "Array Manipulation", "", "", "", "", "", ""],
        
        # Stacks
        [16, "Stack", "Basic Stack", "Valid Parentheses", "https://leetcode.com/problems/valid-parentheses/", "Easy", "Balanced Brackets", "", "", "", "", "", ""],
        [17, "Stack", "Basic Stack", "Remove Outermost Parentheses", "https://leetcode.com/problems/remove-outermost-parentheses/", "Easy", "Duplicate Brackets", "", "", "", "", "", ""],
        [18, "Stack", "Basic Stack", "Remove All Adjacent Duplicates In String", "https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/", "Easy", "Stack Basics", "", "", "", "", "", ""],
        [19, "Stack", "Design", "Min Stack", "https://leetcode.com/problems/min-stack/", "Medium", "Minimum Stack - 1", "", "", "", "", "", ""],
        [20, "Stack", "Evaluation", "Evaluate Reverse Polish Notation", "https://leetcode.com/problems/evaluate-reverse-polish-notation/", "Medium", "Postfix Evaluations", "", "", "", "", "", ""],
        [21, "Stack", "Monotonic Stack", "Next Greater Element I", "https://leetcode.com/problems/next-greater-element-i/", "Easy", "Next Greater Element on the Right", "", "", "", "", "", ""],
        [22, "Stack", "Monotonic Stack", "Daily Temperatures", "https://leetcode.com/problems/daily-temperatures/", "Medium", "Next Greater Element on the Right", "", "", "", "", "", ""]
    ]

    wb = Workbook()
    ws = wb.active
    ws.title = "DSA Tracker"

    # Write Data
    for row in data:
        ws.append(row)

    # Styles
    header_fill = PatternFill(start_color="1F497D", end_color="1F497D", fill_type="solid")
    header_font = Font(bold=True, color="FFFFFF")
    center_align = Alignment(horizontal="center", vertical="center")
    left_align = Alignment(horizontal="left", vertical="center")
    
    thin_border = Border(left=Side(style='thin', color='BFBFBF'), 
                         right=Side(style='thin', color='BFBFBF'), 
                         top=Side(style='thin', color='BFBFBF'), 
                         bottom=Side(style='thin', color='BFBFBF'))

    # Format header
    for cell in ws[1]:
        cell.fill = header_fill
        cell.font = header_font
        cell.alignment = center_align
        cell.border = thin_border

    # Format rows
    alt_fill = PatternFill(start_color="F2F2F2", end_color="F2F2F2", fill_type="solid")
    for row_idx, row in enumerate(ws.iter_rows(min_row=2), start=2):
        for cell in row:
            cell.border = thin_border
            # Alternating row colors
            if row_idx % 2 == 0:
                cell.fill = alt_fill
            
            # Formatting difficulty column (now at column 6 because of No.)
            if cell.column == 6:
                if cell.value == "Easy":
                    cell.font = Font(color="00B050", bold=True)
                elif cell.value == "Medium":
                    cell.font = Font(color="E36C09", bold=True)
                elif cell.value == "Hard":
                    cell.font = Font(color="FF0000", bold=True)
                    
            # LeetCode links
            if cell.column == 5 and cell.value and isinstance(cell.value, str) and cell.value.startswith("http"):
                cell.hyperlink = cell.value
                cell.font = Font(color="0563C1", underline="single")
            
            # Alignments (No. is at 1)
            if cell.column in [2, 3, 4, 5, 7]:
                cell.alignment = left_align
            else:
                cell.alignment = center_align

    # Column Widths
    widths = {
        'A': 5, 'B': 15, 'C': 20, 'D': 45, 'E': 45, 'F': 15,
        'G': 35, 'H': 15, 'I': 18, 'J': 15, 'K': 18, 'L': 18, 'M': 18
    }
    for col, width in widths.items():
        ws.column_dimensions[col].width = width

    ws.auto_filter.ref = ws.dimensions
    ws.freeze_panes = "A2"

    try:
        wb.save(xlsx_file)
        print("Successfully created formatted Excel file.")
    except PermissionError:
        print("ERROR_OPEN")

if __name__ == "__main__":
    generate_tracker()
