#!/bin/bash

# Script to run all test files and capture output

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
    "test_single_union.c"
    "test_single_enum.c"
    "test_single_var_init.c"
    
    # Simple tests
    "test_simple_var.c"
    "test_simple_expr.c"
    "test_simple_stmt.c"
    "test_multi_simple.c"
    
    # Complex tests
    "test_complex_expr.c"
    "test_complex_stmt.c"
    
    # Feature-specific tests
    "test_expressions.c"
    "test_statements.c"
    "test_declarations.c"
    "test_if_else.c"
    "test_not.c"
    "test_typedef.c"
    "test_actual_typedef.c"
    "test_fun_decl.c"
    "test_fun_def.c"
    
    # Variable declaration tests
    "test_var_dec.c"
    "test_vardec.c"
    "test_var_init.c"
    "test_var_array.c"
    "test_var_pointer.c"
    "test_var_const.c"
    "test_var_static.c"
    
    # Comprehensive tests
    "test_declarations_only.c"
    "test_comprehensive.c"
    "test_all_combined.c"
    "test_clean.c"
    "test_no_comment.c"
    "test_edge_cases.c"
    "test_array_alt.c"
    "test.c"
)

passed=0
failed=0
skipped=0

# Change to tests directory
cd tests 2>/dev/null || { echo "Error: tests directory not found"; exit 1; }

# Run each test
for test in "${tests[@]}"; do
    if [ -f "$test" ]; then
        echo "----------------------------------------"
        echo "Testing: $test"
        echo "----------------------------------------"
        
        # Run the parser and capture output
        if java Parse.Main "$test" > /dev/null 2>&1; then
            echo "PASSED"
            ((passed++))
        else
            echo "FAILED"
            # Show error for failed tests
            java Parse.Main "$test" 2>&1 | tail -5
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
