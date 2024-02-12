/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const url = "http://localhost:8080/task/user/1";

function hideLoader(){
    document.getElementById("loading").style.display = "none";
}

function show(tasks){
    let tab = `<thead>
    <tr>
        <th>#</th>
        <th>Description</th>
        <th>Username</th>
        <th>User_Id</th>
    </tr>
    </thead>`;
    
    for (let task of tasks){
        tab += `
            
            <tbody> 
                <tr> 

                    <td>${task.id}</td> 
                    <td>${task.description}</td> 
                    <td>${task.user.username}</td> 
                    <td>${task.user.id}</td> 

                </tr>
            </tbody>
        `;
    }
    tab +=  ` <tfoot>
                <tr> 
                    <td colspan="4"> Todas as tarefas do usuário</td> 
                </tr>
            </tfoot>
           `
    
    document.getElementById("tasks").innerHTML = tab;
}

async function getAPI(url){
    const response = await fetch(url, {method: "GET"});
    
    var data = await response.json(); 
    console.log(data);
    if(response)
        hideLoader();
    show(data);
}

getAPI(url);