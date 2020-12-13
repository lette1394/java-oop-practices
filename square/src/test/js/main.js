const matrix = [
  ['R', 'R', 'W'],
  ['G', 'C', 'W'],
  ['G', 'B', 'B'],
]

const subject = square(matrix);

subject.pushUp(0);
subject.pushUp(0);
subject.pushUp(0);

const expected = "RRW\nGCW\nGBB";
expect(subject.print()).toBe(expected)
// 값이 일치하면 pass,
// 값이 다르면 실패했다고 나옴