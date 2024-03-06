const ctx = document.getElementById('myChart');

fetch("../result.txt")
.then(resp => resp.text())
.then(data => {
    console.log(data)

    let a = data.split("\n")

    console.log(a)
    
    a[0] = a[0].split(',')
    a[1] = a[1].split(',')

    console.log(a)

    a[0] = a[0].map(s => parseInt(s))
    a[1] = a[1].map(s => parseInt(s))

    console.log(a)

    criarTabela(a)
})

function criarTabela(dados) {
    
    const numbers = Array.from({length: dados[0].length}, (_, index) => index + 1)
    
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: numbers,
            datasets: [
                {
                    label: 'A function',
                    data: dados[0],
                    borderWidth: 1
                },
                {
                    label: 'B function',
                    data: dados[1],
                    borderWidth: 1
                },
                
    
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            layout: {
                padding: 50
            }
        }
    });
}
