#!/bin/bash

# Script to run all test files and capture output
# Must be run from the project root directory

# Get the directory where the script is located
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || { echo "Error: Could not change to script directory"; exit 1; }

echo "================================================"
echo "Running Parser Tests"
echo "================================================"
echo ""

# Array of test files (organized by category)
tests=(
    # Single feature tests
    "test_single_var.c"
    "test_single_typedef.c"
    "test_single_struct.c"
    "test_single_var_init.c"
    
    # Simple tests
    "test_simple_var.c"
    "test_multi_simple.c"
    
    # Feature-specific tests
    "test_if_else.c"
    "test_not.c"
    "test_typedef.c"
    "test_actual_typedef.c"
    "test_fun_decl.c"
    "test_fun_def.c"
    
    # Variable declaration tests
    "test_var_dec.c"
    "test_var_init.c"
    "test_var_array.c"
    "test_var_pointer.c"
    "test_var_const.c"
    "test_var_static.c"
    
    # Comprehensive tests
    "test_declarations_only.c"
    "test_array_alt.c"
    "test.c"
)

passed=0
failed=0
skipped=0

# Run each test
for test in "${tests[@]}"; do
    testfile="tests/$test"
    if [ -f "$testfile" ]; then
        echo "----------------------------------------"
        echo "Testing: $test"
        echo "----------------------------------------"
        
        # Run the parser and capture output (from project root)
        if java Parse.Main "$testfile" > /dev/null 2>&1; then
            echo "PASSED"
            ((passed++))
        else
            echo "FAILED"
            # Show error for failed tests
            java Parse.Main "$testfile" 2>&1 | tail -5
            ((failed++))
        fi
        echo ""
    else
        echo "⚠ SKIPPED: $test (file not found)"
        ((skipped++))
        echo ""
    fi
done

echo "================================================"
echo "Test Summary"
echo "================================================"
echo "Passed:  $passed"
echo "Failed:  $failed"
echo "Skipped: $skipped"
echo "Total:   $((passed + failed))"
echo ""

if [ $failed -eq 0 ]; then
    echo "All tests passed!"
    exit 0
else
    echo "$failed test(s) failed"
    exit 1
fi
