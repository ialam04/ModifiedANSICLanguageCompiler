#!/bin/bash

# Script to run all test files and capture output

echo "================================================"
echo "Running Parser Tests"
echo "================================================"
echo ""

# Array of test files
tests=(
    "test_expressions.c"
    "test_statements.c"
    "test_declarations.c"
    "test_all_combined.c"
    "test_edge_cases.c"
    "test_simple_var.c"
    "test_simple_expr.c"
    "test_simple_stmt.c"
    "test_complex_expr.c"
    "test_complex_stmt.c"
    "test_var_dec.c"
    "test_comprehensive.c"
    "test_no_comment.c"
    "test_clean.c"
)

# Run each test
for test in "${tests[@]}"; do
    if [ -f "$test" ]; then
        echo "----------------------------------------"
        echo "Testing: $test"
        echo "----------------------------------------"
        java Parse.Main "$test"
        exit_code=$?
        if [ $exit_code -eq 0 ]; then
            echo "✓ PASSED"
        else
            echo "✗ FAILED (exit code: $exit_code)"
        fi
        echo ""
    fi
done

echo "================================================"
echo "Test run complete"
echo "================================================"
