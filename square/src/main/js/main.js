const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

rl.on("line", function (line) {
  console.log("hello !", line);
  rl.close();
})
.on("close", function () {
  process.exit();
});

const square = function () {
  // TODO: 외부에서 주입받기
  const matrix = [
    ['R', 'R', 'W'],
    ['G', 'C', 'W'],
    ['G', 'B', 'B'],
  ]

  const updater = {
    updateRow: function (row, newLine) {
      for (let i = 0; i < 3; i++) {
        matrix[row][i] = newLine[i];
      }
    },
    updateCol: function (col, newLine) {
      for (let i = 0; i < 3; i++) {
        matrix[i][col] = newLine[i];
      }
    }
  }

  const extractor = {
    getRowAt: function (base) {
      const ret = []
      for (let i = 0; i < 3; i++) {
        ret.push(matrix[i][base]);
      }
      return ret;
    },
    getColAt: function (base) {
      const ret = []
      for (let i = 0; i < 3; i++) {
        ret.push(matrix[i][base])
      }
      return ret;
    }
  }

  return {
    pushUp: function (col) {
      const oldLine = extractor.getColAt(col);

      const first = oldLine[0];
      const rest = oldLine.slice(1, 3);

      const newLine = [...rest, first];
      updater.updateCol(col, newLine);
    },
    pushDown: function (col) {
    },
    pushLeft: function (row) {
    },
    pushRight: function (row) {
    },
    print: function () {
      for (let i = 0; i < 3; i++) {
        let line = '';
        for (let j = 0; j < 3; j++) {
          line += matrix[i][j]
        }
        console.log(line)
      }
    }
  }
}()

square.pushUp(0);
square.print();
