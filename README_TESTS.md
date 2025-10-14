# Parser Test Suite

This directory contains comprehensive test files for the C language parser implementation.

## Test Files

### Expression Tests
- **test_expressions.c** - Tests all expression types including:
  - Arithmetic operators (+, -, *, /, %)
  - Bitwise operators (&, |, ^, ~, <<, >>)
  - Logical operators (&&, ||)
  - Relational operators (<, >, <=, >=, ==, !=)
  - Assignment operators (=, +=, -=, *=, /=, %=, etc.)
  - Unary operators (++, --, -, +, ~, &, *)
  - Array subscripting
  - Function calls
  - Field access (. and ->)
  - Cast expressions
  - Sizeof operator
  - Comma operator

### Statement Tests
- **test_statements.c** - Tests all statement types including:
  - Compound statements (blocks)
  - If and if-else statements
  - While loops
  - Do-while loops
  - For loops
  - Labeled statements
  - Goto statements
  - Break and continue
  - Return statements
  - Expression statements
  - Case and default statements

### Declaration Tests
- **test_declarations.c** - Tests all declaration types including:
  - Variable declarations (int, char, float, double)
  - Variable initialization
  - Bitfield modifiers (const, volatile, static, extern, auto, register)
  - Pointer declarations (single and multiple levels)
  - Array declarations (single and multi-dimensional)
  - Typedef declarations
  - Struct declarations
  - Union declarations
  - Enum declarations
  - Function declarations (prototypes)
  - Function definitions

### Combined Tests
- **test_all_combined.c** - Integration test combining declarations, statements, and expressions in realistic code patterns

### Edge Case Tests
- **test_edge_cases.c** - Tests corner cases including:
  - Empty blocks
  - Nested blocks
  - Complex pointer/array combinations
  - Multiple indirection levels
  - Empty parameter lists
  - Variadic functions
  - Complex bitfield combinations
  - Deeply nested structures
  - Edge case expressions

### Simple Tests (Existing)
- **test_simple_var.c** - Basic variable test
- **test_simple_expr.c** - Basic expression test
- **test_simple_stmt.c** - Basic statement test
- **test_complex_expr.c** - Complex expression test
- **test_complex_stmt.c** - Complex statement test
- **test_var_dec.c** - Variable declaration test
- **test_comprehensive.c** - Comprehensive test
- **test_no_comment.c** - Test without comments
- **test_clean.c** - Clean test

## Running Tests

### Individual Test
```bash
java Parse.Main test_expressions.c
java Parse.Main test_declarations.c
```

### All Tests
```bash
chmod +x run_all_tests.sh
./run_all_tests.sh
```

Or manually:
```bash
for f in test_*.c; do echo "Testing $f"; java Parse.Main "$f"; echo ""; done
```

## Expected Output

Each test should parse successfully and print the abstract syntax tree in a structured format showing:
- Declaration types (var-dec, fun-dec, typedef, struct, union, enum)
- Type information (int, char, float, pointers, arrays)
- Bitfield flags (const, volatile, static, extern, auto, register)
- Statement structure (compound, if, while, for, return, etc.)
- Expression trees with operators and operands

## Grammar Coverage

These tests cover the following grammar productions:
- **Declarations**: VAR, FUN, TYPEDEF, STRUCT, UNION, ENUM
- **Types**: Basic types, pointers, arrays, combinations
- **Statements**: All statement types from the C grammar
- **Expressions**: All expression types with proper precedence
- **Bitfields**: All storage class and type qualifiers

## Notes

- Parser expects lowercase keywords (var, fun) not uppercase (VAR, FUN)
- Start symbol is `declaration` so tests should contain declarations
- The `-compact_red` flag is required for compilation due to grammar size
- Some tests may need to be run individually if they test single constructs
