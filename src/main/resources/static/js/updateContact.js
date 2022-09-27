async function updateContact(){
    const phone = document.getElementById('phoneInput').value
    const name = document.getElementById('nameInput').value


    const pathname = window.location.pathname
    const pathArr = pathname.split('/')
    /*const id = BigInt(pathArr[pathArr.length - 1])*/
    const id = parseInt(pathArr[pathArr.length - 1])
    /*const id = pathArr[pathArr.length - 1]*/
    console.log(id)

    /*const id = pathname[pathname.length - 1]*/

    const res = await fetch('http://localhost:8080/update', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(id, phone, name)
    })
}