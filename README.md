# Project 2: C Language Parser with Declarations and Expressions

## Team Members and Contributions

### Jackson Thomas
**Project 2**: Complete implementation of statement grammar including

**Project 1**: Main file implementation, punctuation tokens, and comment handling

### Jackson Descant
**Project 2**: Complete implementation of declaration grammar including:

**Project 1**: Integer literal implementation

### Ibrahim Alam
**Project 2**: Complete implementation of expression grammar including:

Additionally:
- Skeleton Absyn structure and hierarchy
- Print.java implementation for parse tree visualization
- Visitor interface for AST traversal
- Precedence rule implementation for conflict resolution

**Project 1**: String literals and escape sequences (\n, \t, \r, \b, \f, \v, \\, \', \", octal, hexadecimal)

## Design Decisions

### 1. Program Start Symbol
**Decision**: Use `program` as the start symbol that produces `declaration_list`.

**Rationale**: The `program` non-terminal acts as the handoff point to `declaration_list`. This was primarily a structural preference but the functionality still enables comprehensive parsing. 

### 2. Visitor Pattern for AST Traversal
**Decision**: Implemented a Visitor interface for Print.java to interact with Absyn nodes.

**Rationale**: The Visitor pattern provides:
- Clean separation between AST structure and tree operations
- Extensibility for future compiler phases (semantic analysis, code generation)
- Type-safe traversal without instanceof checks
- Centralized printing logic in Print.java

### 3. Precedence Rules
**Decision**: Added explicit precedence rules ONLY for the dangling else conflict:
```cup
precedence nonassoc IF;
precedence nonassoc ELSE;
```

**Rationale**: 
- The dangling else is a well-known ambiguity that benefits from explicit resolution
- By declaring ELSE with higher precedence than IF, the parser correctly shifts on ELSE, associating it with the nearest IF
- This matches standard C semantics for nested if-else statements

**Decision**: Did NOT implement explicit precedence rules for operators.

**Rationale**: Our grammar inherently handles operator precedence through its layered structure:
```
expression → assignment_expression → conditional_expression → 
logical_OR_expression → logical_AND_expression → ... → 
multiplicative_expression → cast_expression → unary_expression → 
postfix_expression → primary_expression
```

Each layer enforces the correct precedence level without requiring `%prec` directives. This approach:
- Is more maintainable and readable
- Matches standard compiler construction practices
- Avoids potential conflicts from over-specified precedence rules
- Naturally expresses C's precedence hierarchy

### 4. Remaining Conflicts
**Decision**: Left 3 conflicts unresolved in the grammar.

**Rationale**: These conflicts are **inherent to the C language grammar** when using an LALR(1) parser like CUP:

**Conflict Details**: All 3 conflicts occur in state #167 involving the ambiguity between `type_name ::= ID` and `primary_expression ::= ID` under RPAREN. This arises in expressions like `(identifier)` where the parser cannot determine if the identifier is a type name (cast) or variable name (parenthesized expression) without semantic information.

**Why Not Harmful**:
- This is a **known limitation of context-free grammars** for C
- Real C compilers resolve this using a **symbol table** during semantic analysis
- CUP's default conflict resolution (favoring shift) produces correct parse trees for all valid C syntax
- All our test files parse correctly with these conflicts present

**Could Be Resolved**: Yes, these conflicts could be resolved easily by:
- Introducing semantic checks during parsing (symbol table lookup)
- Using GLR parsing instead of LALR(1)
- Restructuring the grammar to avoid the ambiguity (at the cost of readability)

However, for a course project focused on parsing only (no semantic analysis), these conflicts are acceptable and standard.

## AI Tools and Resources

### AI Tools Used
- **Claude / ChatGPT**:
  - **Learning CUP syntax**: Understanding precedence declarations, terminal/non-terminal definitions, and semantic actions
  - **Absyn node construction advice**: Guidance on proper AST node design, constructors, and relationships
  - **Debugging**: Help diagnosing syntax errors, type mismatches, and compilation issues in the terminal
  - **Grammar conflict analysis**: Understanding shift/reduce and reduce/reduce conflicts, and strategies for resolution
  - **Lexer issues**: Debugging token recognition problems and rule ordering in JLex
  - **Documentation**: Cleaning up and structuring raw content written in readme

### Internet Resources
- CUP Manual (v0.10g) for parser generator syntax
- ANSI C89 specification for grammar rules

## What Works

### Fully Implemented and Tested

1. **Complete Expression Grammar** - All C operators with correct precedence
2. **Complete Statement Grammar** - All control flow, loops, and compound statements  
3. **Complete Declaration Grammar** - Variables, functions, typedefs, structs, unions, enums
4. **Multi-declaration parsing** - Files with multiple declarations
5. **Lexer** - All tokens, string/char literals with escape sequences
6. **AST and Visitor** - Complete node hierarchy with print functionality
7. **Conflict Resolution** - Zero reduce/reduce conflicts, minimal shift/reduce conflicts

### Test Files
- `test.c` - Comprehensive test (parses successfully)
- `test_declarations_only.c` - Declaration-focused tests
- `test_multi_simple.c` - Multiple variable declarations
- `test_if_else.c` - If-else statement testing
- Individual `test_single_*.c` files for targeted feature testing
- Script `run_all_tests.sh` to run all tests in tests folder

All test files parse without errors and produce correct parse trees.

## Parsing Conflicts Summary

| Conflict Type | Count | Status |
|--------------|-------|--------|
| Reduce/Reduce (Type Ambiguity) | 1 | Acceptable (inherent to C grammar) |
| Shift/Reduce (Dangling Else) | 0 | Resolved with precedence |
| Shift/Reduce (Type Ambiguity) | 2 | Acceptable (inherent to C grammar) |
| **Total** | **3** | **All non-harmful** |

### Detailed Conflict Analysis

All 3 conflicts occur in **state #167** and involve the ambiguity between type names and identifiers:

**1. Reduce/Reduce Conflict:**
```
between type_name ::= ID (*) 
and     primary_expression ::= ID (*) 
under symbols: {RPAREN}
Resolved in favor of the second production.
```

**2-3. Two Shift/Reduce Conflicts:**
```
between type_name ::= ID (*) 
under symbol RPAREN
Resolved in favor of shifting.

between primary_expression ::= ID (*) 
under symbol RPAREN
Resolved in favor of shifting.
```

**Why These Conflicts Exist:**
In expressions like `(identifier)`, the parser cannot determine without semantic information whether:
- `identifier` is a **type name** → cast expression like `(int)x`
- `identifier` is a **variable name** → parenthesized expression like `(x)`

This is a fundamental limitation of context-free LALR(1) parsing for C. Real C compilers resolve this using symbol table lookups during semantic analysis. CUP's default resolution correctly handles all valid C syntax in our test suite.

## Error Recovery

**Implementation**: Minimal - relies on CUP's default error recovery.

When syntax errors are detected:
1. Parser reports error location (file:line.column)
2. Attempts automatic recovery by skipping tokens
3. Terminates if recovery fails

**Rationale**: The project focuses on correct parsing of valid input. CUP's default recovery is sufficient for identifying syntax errors without the complexity of custom error productions.
