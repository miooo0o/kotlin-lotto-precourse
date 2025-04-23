# Lotto

Lotto is a simple command-line application that simulates a lottery ticket system. Users can purchase tickets, check
them against winning numbers, and calculate their winnings.

## Implementation Requirements

| Requirement        | Description                                                                                                                 |
|--------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Code Style         | Keep functions under 10 lines to ensure they perform a single responsibility                                                |
| Control Flow       | Avoid using `else`                                                                                                          |
| Design Patterns    | Use `Enum` classes where applicable                                                                                         |
| Testing            | Implement unit tests for all logic, except for UI interactions                                                              |
| External APIs      | Use the Randoms and Console APIs provided by the `camp.nextstep.edu.missionutils package`                                   |
| Exception Handling | If the user inputs invalid data, the program must throw an `IllegalArgumentException` and re-prompt input from that step    |
| Exception Types    | Handle *only* specific exception types such as `IllegalArgumentException` or `IllegalStateException`, not generic Exception |

### Random Number Generation

To generate random values, use `Randoms.pickUniqueNumbersInRange()` from `camp.nextstep.edu.missionutils.Randoms`.

Example:

```kotlin
Randoms.pickUniqueNumbersInRange(1, 45, 6)
```

### User Input

To receive user input, use `Console.readLine()` from `camp.nextstep.edu.missionutils.Console`.

## Development Approach

> I'm trying my best to learn and practice TDD with this project!

## Architecture

### Application Flow

```
Input(object) → Validator(object or inline) → Logic(class) → Output(object)
```

### Class Structure

- **Lotto**: Core domain class representing a lottery ticket
- **LottoPolicy**: Contains constants and validation rules for the lottery domain
- **Validator**: Handles input validation logic
- **InputView/OutputView**: Manage user interaction (input/output)
- **Application**: Entry point containing the main function

## Feature List

### Input Processing

- [ ] Accept purchase amount
- [ ] Accept winning numbers (6 unique numbers)
- [ ] Accept bonus number

### Validation

- [x] Validate purchase amount (must be divisible by 1,000)
- [x] Validate numbers are within range (1-45)
- [x] Validate numbers have no duplicates
- [x] Validate bonus number is not among winning numbers
- [ ] Move logic functions out of `LottoPolicy` into `Validator`
- [ ] Remove `fun` definitions from `LottoPolicy` entirely
- [ ] Create error → message mapper for consistent `[ERROR]: ...` output

### Error Handling

- [x]

### Exception Handling

- [x] Handle invalid purchase amount
- [x] Handle invalid number range
- [x] Handle duplicate numbers
- [x] Handle incorrect number count
- [x] Display error messages with `[ERROR]:` prefix
- [ ] Add error validation to `InputView` and loop on invalid input
- [ ] Integrate error handling in full application flow (input → validate → retry)
- [ ] Manage retry loops in controller components
- [ ] Use try-catch blocks to catch exceptions and guide user through input correction

### Lotto Generation

- [ ] Generate lotto tickets based on purchase amount
- [ ] Create random numbers for each ticket
- [ ] Sort lotto numbers in ascending order
- [ ] Display purchased tickets

### Winner Calculation

- [ ] Compare each ticket with winning numbers
- [ ] Determine match count for each ticket
- [ ] Check for bonus number match
- [ ] Categorize winning tickets by rank
    - 1st: 6 matches
    - 2nd: 5 matches + bonus
    - 3rd: 5 matches
    - 4th: 4 matches
    - 5th: 3 matches

### Result Output

- [ ] Display winning statistics
- [ ] Calculate total return rate
- [ ] Format return rate to one decimal place

## Exception Strategy

The application will use two primary exception types as required:

- IllegalArgumentException: For input validation failures (invalid numbers, formats, etc.)
- IllegalStateException: For application state-related errors (if any occur)

The LottoError class will be responsible for:

1. Connecting LottoErrorType with the appropriate exception type
2. Generating properly formatted error messages
3. Throwing the correct exception with consistent messaging

Input retry logic will be managed in the controller layer, which will:

1. Catch specific exceptions
2. Display appropriate error messages
3. Allow users to retry input from the failed step

## Test Progress

### Completed Tests

- [x] Validator tests for purchase amount validation
- [x] Validator tests for lottery number validation
- [x] Validator tests for bonus number validation
- [x] Error message generation tests

### Planned Tests

- [ ] Exception handling tests with retry scenarios
- [ ] Lotto ticket generation tests
- [ ] Match calculation tests
- [ ] Prize calculation tests

---

## Example Execution

```
Please enter the purchase amount.
8000

You have purchased 8 tickets.
[8, 21, 23, 41, 42, 43] 
[3, 5, 11, 16, 32, 38] 
[7, 11, 16, 35, 36, 44] 
[1, 8, 11, 31, 41, 42] 
[13, 14, 16, 38, 42, 45] 
[7, 11, 30, 40, 42, 43] 
[2, 13, 22, 32, 38, 45] 
[1, 3, 5, 14, 22, 45]

Please enter last week's winning numbers.
1,2,3,4,5,6

Please enter the bonus number.
7

Winning Statistics
---
3 Matches (5,000 KRW) - 1 ticket
4 Matches (50,000 KRW) - 0 tickets
5 Matches (1,500,000 KRW) - 0 tickets
5 Matches + Bonus Ball (30,000,000 KRW) - 0 tickets
6 Matches (2,000,000,000 KRW) - 0 tickets
Total return rate is 62.5%.
```