$(document)
		.ready(
				function() {

					$('#back').click(function() {
						window.location.href = hostURL + "login.html";
					});

					$('#registerUser')
							.click(
									function() {
										var emailId = $('#emailId').val();
										var password = $('#password').val();
										var retypePassword = $(
												'#retypePassword').val();
										var firstName = $('#firstName').val();
										var lastName = $('#lastName').val();
										var phone = $('#phone').val();
										var company = $('#company').val();
										var userRole = $('#userRole').val();
										var address = $('#address').val();

										if (password == ""
												|| retypePassword == ""
												|| emailId == ""
												|| firstName == ""
												|| phone == "" || company == ""
												|| userRole == ""
												|| address == "") {
											alert("All fields are Mandatory. Please make sure you entered all details ");
										}

										if (password !== retypePassword) {
											alert("Password no not match");
										}

										var userDTO = new Object();
										userDTO.lastName = lastName;
										userDTO.firstName = firstName;
										userDTO.phoneNumber = phone;
										userDTO.emailAddress = emailId;
										userDTO.userRole = userRole;
										userDTO.companyName = company;
										userDTO.address = address;
										userDTO.password = password;

										console.log(userDTO);

										// Ajax call to get the register User
										$
												.ajax({
													url : "user/register",
													type : "POST",
													dataType : 'json',
													contentType : "application/json",
													data : JSON
															.stringify(userDTO),
													success : function(result) {
														alert("User Registered Successfully!!")
														window.location.href = hostURL
																+ "login.html"
													}
												});

									});

				});