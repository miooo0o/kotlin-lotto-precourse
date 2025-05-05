### Feature List

#### Input processing

- [x] Accept purchase amount
- [x] Accept winning numbers (6 unique numbers)
- [x] Accept bonus number

#### Validation

- [x] purchase amount % 1000 == 0 && purchase amount / 1000 > 0
- [x] numbers within range (1 - 45)
- [x] numbers have no duplicate
- [x] bonus number is not among winning numbers

### Error

- [x] error -> `[ERROR]: ...`
- [x] Handle only specific exception types
    - IllegalArgumentException
    - IllegalStateException

### Number generate

- [x] Use camp.nextstep.edu.missionutils
    - `Randoms.pickUniqueNumbersInRange(1, 45, 6)`

### Winning calculation

- [ ] Categorize winning tickets by rank
    - [ ] Use enum class
        - 1st: 6 matches
        - 2nd: 5 matches + bonus
        - 3rd: 5 matches
        - 4th: 4 matches
        - 5th: 3 matches
- [ ] compare each ticket with winning numbers

