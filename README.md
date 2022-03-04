# kotlin-minesweeper

# 용어정리

- Block(블록): 지뢰 혹은 주변에 지뢰가 몇개있는지 알려주는 한 픽셀

```
◻️ ◻️ ◻️
️◻️ ◻️ ◻️
◻️ ◻️ ◻️

   ↓

1  1  1
1  *  1
1  1  1
```

- Mine(지뢰): 말 그대로 지뢰
    - Sweep(지뢰탐색): 만들어진 지뢰를 찾는다
    - Allocate(숫자할당): 만들어진 지뢰를 기반으로 주변에 숫자블록을 할당한다
- Flag(플래그): 지뢰라고 의심되는 블록을 지뢰인 것으로 플래그를 세우는 액션
- Chord(코드): (지뢰나 플래그가 아닌 빈 블록 == 숫자 블록) 에 코드 액션을 주면 주변의 블록을 오픈 시킨다.
- Open(오픈): 블록을 오픈한다. ~~오픈하게 되면 3가지 상태가 존재한다.~~ ~~NUMBER, BLANK, MINE~~
    - ~~NUMBER: 숫자를 가지고 있는 블록~~
    - ~~BLANK: 빈 블록~~
    - ~~MINE: 지뢰를 가지고 있는 블록~~

# 기능 요구사항

- 3x3, 6x6, 9x9, 12x12 와 같은 형태로 지뢰찾기를 할 수 있다.
- 생성되는 지뢰의 숫자는 기본적으로 주어진 블록의 1/3 만큼 이다.
- 지뢰를 오픈 했을 때 지뢰가 있으면 패배 하게 되고 게임은 종료된다.
- 지뢰를 제외한 모든 블록을 오픈하면 게임에 승리한다.

# 게임 진행 예시

```
지뢰찾기 보드의 형태를 입력해주세요. 예) 3,3
3,3

◻️(0,0) ◻️(0,1) ◻️(0,2)
️◻️(1,0) ◻️(1,1) ◻️(1,2)
◻️(2,0) ◻️(2,1) ◻️(2,2)

좌표를 선택해주세요. 예) 1,1
0,0

명령을 선택해주세요.
1. 오픈
2. 플래그
3. 물음표
1

1(0,0) ◻️(0,1) ◻️(0,2)
️◻️(1,0) ◻️(1,1) ◻️(1,2)
◻️(2,0) ◻️(2,1) ◻️(2,2)

좌표를 선택해주세요. 예) 1,1
1,1

명령을 선택해주세요.
1. 오픈
2. 플래그
3. 물음표
1

1(0,0) 2(0,1) *(0,2)
1(1,0) *(1,1) 2(1,2)
1(2,0) 1(2,1) 1(2,2)

Game Over
1. 재시작
2. 종료
```