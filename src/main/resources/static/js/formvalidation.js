$(document).ready(function() {
/***Login form Validation******/
    $('form#home_login').bootstrapValidator({
        message: 'This value is not valid',         
        fields: {
            networkloginid: {
                message: 'This Field is required',
                validators: {
                    notEmpty: {
                        message: 'This Field is required'
                    }/*,
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The username must be more than 6 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: 'The username can only consist of alphabetical, number, dot and underscore'
                    }*/
                }
            },
            institution: {
                validators: {
                    notEmpty: {
                        message: 'This Field is required'
                    }
                }
            },
            loginpassword: {
                validators: {
                    notEmpty: {
                        message: 'This Field is required'
                    },                     
                }
            }            
        }
    });
	/***Collection Tab Modal cgd deaccessionform form Validation******/
	$('form#cgd_deaccessionform').bootstrapValidator({
        message: 'This value is not valid',         
        fields: { 
			newCGD: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				},
			CGDChangeNotes: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				},
				DeliveryLocation: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				},
				DeaccessionNotes: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				}                   
        }
    });
	/***Request Tab Create Request Form Validation******/
	$('form#createrequestfrom').bootstrapValidator({
        message: 'This value is not valid',         
        fields: { 
			itembarcode: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
							},
						 integer: {
                        message: 'Enter Numbers Only'
						}
					}
				},
ItemOwningInstitution: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				},
				PatronBarcode: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						},
						 integer: {
                        message: 'Enter Numbers Only'
						}
					}
				},
		PatronEmailAddress: {
                validators: {
                    notEmpty: {
                        message: 'This Field is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },	
requesttype: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				},
				DeliveryLocation: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				},
				StartPage: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						},
						 integer: {
                        message: 'Enter Numbers Only'
						}
					}
				},
				EndPage: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						},
						 integer: {
                        message: 'Enter Numbers Only'
						}
					}
				},
				VolumeNumber: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						},
						 integer: {
                        message: 'Enter Numbers Only'
						}
					}
				},	
				Issue: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				},
				ArticleAuthor: {
					validators: {
						notEmpty: {
							message: 'This Field is required'
						}
					}
				}				
			}
    }).on('success.form.bv', function(e) { 
            e.preventDefault();
            var $form     = $(e.target),
                validator = $form.data('bootstrapValidator');				
            $('.modal.create-request-details').modal('show');
			$form
                .bootstrapValidator('disableSubmitButtons', false) // Enable the submit buttons
                .bootstrapValidator('resetForm', true);  
			$('form#createrequestfrom')[0].reset();
 });		
});