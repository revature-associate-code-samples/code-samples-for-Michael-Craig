/**
 * 
 */

window.onload = function(){
	loadHomeView();
	$('#homeNav').on('click', loadHomeView);
	$('#loginNav').on('click', loadLoginView);
	$('#signUpNav').on('click', loadSignUpView);
	$('#logoutNav').on('click', logout)
}

function loadHomeView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#gg').on('click', loadLoggedInView);
		}
	}
	xhr.open("GET", "home.view", true);
	xhr.send();	
}

function logout () {
	console.log('logout')
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			loadHomeView();
		}
	}
	xhr.open("GET", "logout", true);
	xhr.send();	
}

// function loadUsers(){
// var xhr = new XMLHttpRequest();
// xhr.onreadystatechange = function(){
// if(xhr.readyState == 4 && xhr.status == 200){
// console.log('1', xhr.responseText);
// usersJSON = JSON.parse(xhr.responseText);
// console.log('2', reimbursements)
// }
// }
// xhr.open("GET", "home");
// xhr.send();
// }

function loadLoginView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#submitLogin').on('click', login)
		}
	}
	xhr.open("GET", "login.view", true);
	xhr.send();	
}

function login() {
	console.log('hit', $('#user').val(), 'hit2', $('#pass').val())
	let user = {
		username: $('#user').val(),
		password: $('#pass').val()
	};
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			loadLoggedInView();
		}
	}
	xhr.open("POST", "login", true)
	xhr.setRequestHeader("Content-type", "application/json");
	const go = JSON.stringify(user);
	console.log('info', go);
	xhr.send(go);
}

function loadSignUpView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#submitLogin').on('click', loadLoggedInView);
		}
	}
	xhr.open("GET", "signUp.view", true);
	xhr.send();	
}

function loadLoggedInView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#createReimBtn').on('click', loadCreateReimPage);
		}
	}
	xhr.open("GET", "test.view", true);
	xhr.send();	
}

function loadCreateReimPage () {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			console.log("first")
		}
	}
	xhr.open("GET", "loggedIn.view", true);
	xhr.send();	
}

//function loadReimbursements(){
//	var xhr = new XMLHttpRequest();
//	xhr.onreadystatechange = function(){
//		if(xhr.readyState == 4 && xhr.status == 200){
//			console.log('1', xhr.responseText);
//			let reimbursements = JSON.parse(xhr.responseText);
//			console.log('2', reimbursements)
//			for(let r of reimbursements) {
//				reimbursementList(r)
//			}
//		}
//	}
//	xhr.open("GET", "employee");
//	xhr.send();
//}
//
//function reimbursementList(r){
//	console.log('generate rbm list')
//	var data = $(`
//		<tr>
//			<th scope="row">${r.id}</th>
//			<td>${r.amount}</td>
//			<td>${r.submitted}</td>
//			<td>${r.resolved}</td>
//			<td>${r.description}</td>
//			<td>${r.author}</td>
//			<td>${r.resolver}</td>
//			<td>${r.status_id}</td>
//			<td>${r.type_id}</td>
//			<td>
//				<button class="fa fa-check"></button>
//				<button class="fa fa-times"></button>
//			</td>
//		</tr>
//		
//	`)
//	$('#reimbursementsList').append(data);
//}

function createReim () {		
	var obj = {
		amount: $('#amount').val(),
		description: $('#description').val(),
		type_id: $('#type').val()
	}
	var toSend = JSON.stringify(obj);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			console.log(xhr.status);
			console.log(xhr.responseText);
			reimbursementList(obj);
		}
	}
	xhr.open("POST", "reimbursement");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(toSend);
}

function loadCreateReimPage () {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("blip")
			$('#view').html(xhr.responseText);
			$('#createRb').on('click', createReim);
		}
	}
	xhr.open("GET", "createReim.view");
	xhr.send();
}

