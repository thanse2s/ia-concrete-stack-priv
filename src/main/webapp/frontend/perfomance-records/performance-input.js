const form = document.querySelector('#standard-form');
const employeeid = document.querySelector('#employeeid');
const goalid = document.querySelector('#goalid');
const companyname = document.querySelector('#companyname');
const targetvalue = document.querySelector('#targetvalue');
const actualvalue = document.querySelector('#actualvalue');
const msg = document.querySelector('.msg');
const recordlist = document.querySelector('#recordlist');

form.addEventListener('submit', onSubmit);

function onSubmit(e) {
    e.preventDefault();
    if (employeeid === '' || goalid === '' || companyname === '' || targetvalue === '' || actualvalue === '') {
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
            `Employee ID : ${employeeid.value},
             Goal ID : ${goalid.value},
             Company Name : ${companyname.value},
             Target Value : ${targetvalue.value},
             Actual Value : ${actualvalue.value}`
        ));
        recordlist.appendChild(li);

        firstname.value = "";
        lastname.value = "";
        id.value = "";
        department.value = "";
    }
}