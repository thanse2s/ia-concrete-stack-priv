const form = document.querySelector('#standard-form');
const firstname = document.querySelector('#firstname');
const lastname = document.querySelector('#lastname');
const id = document.querySelector('#employeeid');
const department = document.querySelector('#department');
const msg = document.querySelector('.msg');
const salesmenList = document.querySelector('#salesmen');

form.addEventListener('submit', onSubmit);

function onSubmit(e) {
    e.preventDefault();
    if (firstname === '' || lastname === '' || id === '' || department === '') {
        msg.classList.add('error');
        msg.innerHTML = "Please enter all fields";
    } else {
        /* Idee: Fetch Api verwenden um Lokale Datei erstellen, die gelesen werden kann
        * -> Problem, wie initialisiere ich das lesen?
        * Aktuell: Nur Listenaufbau unter dem Submit, keine Speicherung
        */
        //fetch('salesmen.json',{
            //method:'POST',
            //headers: {
                //'Accept': 'application/json, text/plain, */*',
                //'Content-type':'application/json'
            //},
            //body:JSON.stringify({title:title, body:body})
            //})
            //.then((res) => res.json())
            //.then((data) => console.log(data))
        const li = document.createElement('li');
        li.appendChild(document.createTextNode(
            `First Name : ${firstname.value},
             Last Name : ${lastname.value}, 
             Employee ID : ${id.value},
             Department : ${department.value}`
        ));
        salesmenList.appendChild(li);

        firstname.value = "";
        lastname.value = "";
        id.value = "";
        department.value = "";
    }
}
