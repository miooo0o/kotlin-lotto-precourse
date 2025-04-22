# Lotto
## Architecture
### Flows
```
Input(object) → Validator(object or inline) → Logic(class) → Output(object)
```
### Check List
- [ ] Keep functions under 10 lines to ensure they perform a single responsibility
- [ ] Avoid using `else`
- [ ] Use `Enum` classes where applicable
- [ ] Implement unit tests for all logic, except for UI interactions
- [ ] must use the Randoms and Console APIs provided by the `camp.nextstep.edu.missionutils package`
  - To generate random values, use `Randoms.pickUniqueNumbersInRange()` from `camp.nextstep.edu.missionutils.Randoms`.
  - example: `Randoms.pickUniqueNumbersInRange(1, 45, 6)`
  - To receive user input, use `Console.readLine()` from `camp.nextstep.edu.missionutils.Console`.
- [ ] If the user inputs invalid data, the program must throw an `IllegalArgumentException` and re-prompt input from that step. 
- [ ] Handle *only* specific exception types such as `IllegalArgumentException` or `IllegalStateException`, not generic Exception.

---

## Feature List

### Input Processing
- [ ] Accept purchase amount
- [ ] Validate purchase amount (must be divisible by 1,000)
- [ ] Accept winning numbers (6 unique numbers)
- [ ] Accept bonus number
- [ ] Validate numbers are within range (1-45)

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

### Exception Handling
- [ ] Create Exception class
- [ ] Handle invalid purchase amount
- [ ] Handle invalid number range
- [ ] Handle duplicate numbers
- [ ] Handle incorrect number count

### Error Handling
- [ ] Error messages must start with `[ERROR]:`

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