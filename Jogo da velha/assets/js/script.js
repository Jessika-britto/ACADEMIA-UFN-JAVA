let currentPlayer = 'X';
let gameBoard = ['', '', '', '', '', '', '', '', ''];
const boardElement = document.getElementById('board');

function makeMove(index) {
    if (gameBoard[index] === '' && !checkWinner()) {
        gameBoard[index] = currentPlayer;
        boardElement.children[index].innerText = currentPlayer;
        document.getElementById('botaoSound').play();
        if (!checkWinner() && !gameBoard.includes('')) {
            document.getElementById('message').innerText = 'Empate!';
            document.getElementById('empateSound').play();
        }
        currentPlayer = (currentPlayer === 'X') ? 'O' : 'X';
    }
}

function resetBoard() {
    gameBoard = ['', '', '', '', '', '', '', '', ''];
    currentPlayer = 'X';
    for (let i = 0; i < boardElement.children.length; i++) {
        boardElement.children[i].innerText = '';
        boardElement.children[i].addEventListener('click', () => makeMove(i));
    }
    document.getElementById('message').innerText = '';
}

function checkWinner() {
    const winPatterns = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];

    for (const pattern of winPatterns) {
        const [a, b, c] = pattern;
        if (gameBoard[a] && gameBoard[a] === gameBoard[b] && gameBoard[a] === gameBoard[c]) {
            document.getElementById('message').innerText = `"${gameBoard[a]}" venceu!`;
            document.getElementById('venceuSound').play();
            disableBoard();
            return true;
        }
    }

    if (!gameBoard.includes('')) {
        document.getElementById('message').innerText = 'Empate!';
        document.getElementById('empateSound').play();
        return true;
    }

    return false;
}

function disableBoard() {
    for (let i = 0; i < boardElement.children.length; i++) {
        boardElement.children[i].removeEventListener('click', () => makeMove(i));
    }
}
