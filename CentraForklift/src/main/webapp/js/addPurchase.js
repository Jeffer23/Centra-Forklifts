
$(document).ready(function() {
	if(sessionStorage.getItem(userIdKey) == null){
		window.location.href = hostURL;
	}
	$("#signedInUser").html(sessionStorage.getItem(userFirstNameKey));
	$("#signout").on( 'click', function () {
		sessionStorage.removeItem(userIdKey);
		sessionStorage.removeItem(userRoleKey);
		sessionStorage.removeItem(userFirstNameKey);
		localStorage.removeItem(invoiceIdKey);
		window.location.href = hostURL;
	} );
	
	var products = [];
	var companyDetails;
	var selectedProducts = [];
	
	function addProduct(){
		var productId = $('#productName').val();
		var filteredProduct = products.filter(prod=> prod.productId == productId)[0];
		var unitPrice = $('#unitPrice').val();
		var quantity = $('#quantity').val();
		var amt = $('#amt').val();
		
		 $( "#addPurchaseTB tbody" ).append( "<tr>" +
          "<td>" + filteredProduct.productName + "</td>" +
          "<td>" + unitPrice + "</td>" +
          "<td>" + quantity + "</td>" +
		   "<td>" + amt + "</td>" +
        "</tr>" );
		 
		 var selectedProduct = new Object();
		 selectedProduct.product = filteredProduct;
		 selectedProduct.quantity = quantity;
		 selectedProduct.totalAmount = amt;
		 selectedProducts.push(selectedProduct);
		
        dialog.dialog( "close" );
	}
	
	var dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 475,
      width: 410,
      modal: true,
      buttons: {
        "Add Product": addProduct,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
		dialog.dialog( "close" );
      }
    });
 
    var form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
     // addProduct();
    });
	
	$('#add').click( function () {
		dialog.dialog( "open" );
	});
	
	$('#productName').change( function () {
		var productId = $('#productName').val();
		var filteredProducts = products.filter(prod=> prod.productId == productId);
		if(filteredProducts.length == 1){
			$('#unitPrice').val(filteredProducts[0].unitPrice);
		}
		else {
			alert("Error : Unknown");
		}
	});
	
	$('#quantity').blur( function () {
		var quantity = $('#quantity').val();
		var unitPrice = $('#unitPrice').val();
		var amount = quantity * unitPrice;
		$('#amt').val(amount);
	});
	
	$('#addPurchaseOrder').click( function () {
    	var userID = new Object();
    	userID.emailAddress=$("#emailId").val();
    	userID.companyName=$("#companyName").val();
    	
    	var purchaseOrder = new Object();
    	purchaseOrder.userID = userID;
    	purchaseOrder.purchaseProducts = selectedProducts;
    	purchaseOrder.billingAddress = $("#billingAddress").val().trim();
    	purchaseOrder.shippingAddress = $("#shippingAddress").val().trim();
    	
    	$.ajax({
     		  url: "purchase/addPurchaseOrder",
     		  type : "POST",
     		  dataType: 'json',
     		  contentType: "application/json",
     		  data: JSON.stringify(purchaseOrder),
     		  success: function(result){
  	   			alert("Purchase Order Added Successfully!!")
  	   			window.location.href = hostURL + "viewPurchaseOrder.html"
     		  }
      	 });
    	
    });
	
	$('#back').click( function () {
		window.location.href = hostURL + "viewPurchaseOrder.html";
    });
	
	$("#companyName").change( function () {
		var companyName = $("#companyName").val();
		var emailIds = companyDetails[companyName];
		$("#emailId option").each(function() {
			 $(this).remove();
		});
		for(var i=0; i<emailIds.length;i++){
			var o = new Option(emailIds[i], emailIds[i]);
			/// jquerify the DOM object 'o' so we can use the html method
			$(o).html(emailIds[i]);
			$("#emailId").append(o);
		}
    });
	
	 // Ajax call to get the PurchaseOrders
    $.ajax({
		  url: "purchase/getAllProducts",
		  success: function(result){
			result.forEach(prod => {
				products.push(prod);
				 var o = new Option(prod.productId, prod.productId);
				/// jquerify the DOM object 'o' so we can use the html method
				$(o).html(prod.productName);
				$("#productName").append(o);
				});
		}
	  });
    
    $.ajax({
		  url: "user/getAllUser?userId=" + sessionStorage.getItem(userIdKey),
		  success: function(result){
			  companyDetails = result;
			  for(var i in result){
				  var o = new Option(i, i);
				/// jquerify the DOM object 'o' so we can use the html method
				$(o).html(i);
				$("#companyName").append(o);
			}
			  
		}
	  });
} );