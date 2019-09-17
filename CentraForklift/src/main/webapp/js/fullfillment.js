function showInvoice(invoiceId){
		localStorage.setItem(invoiceIdKey, invoiceId);
		return true;
}
	
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
	
	function removedSelected(){
		var rows = $('#fullfillmentTB > tbody > tr');
		console.log(rows);
	}
	
	$("#email").on( 'click', function () {
		var selectedPurchaseOrders = t.rows('.selected').data();
		var isValid = true;
		for(var i=0; i<selectedPurchaseOrders.length; i++){
			var selectedPurchaseOrder = selectedPurchaseOrders[i];
			if(selectedPurchaseOrder.invoice == null){
				isValid = false;
				break;
			}
		}
		if(!isValid){
			alert("Some selected Orders are not fullfilled.");
			return;
		}
		
		var sentInvoiceIds = [];
		for(var i=0; i<selectedPurchaseOrders.length; i++){
			var selectedPurchaseOrder = selectedPurchaseOrders[i];
			var invoiceId = selectedPurchaseOrder.invoice.invoiceId;
			var userId = selectedPurchaseOrder.userID.emailAddress;
			
			if(!sentInvoiceIds.includes(invoiceId)){
				sentInvoiceIds.push(invoiceId);
				$.ajax({
				  url: "user/sendEmail?userId=" + userId + "&invoiceId=" + invoiceId,
				  success: function(result){
					  alert("E-mail sent Successfully!!!");
					},
					 error: function(result){
						 alert("Error is Sending email. Please try again after sometime or contact Admin.");
					}
				 });
			}
		}
		removedSelected();
	} );
	
	var t = $('#fullfillmentTB').DataTable({
		"ajax": {
			"url": "purchase/getFullfilledTabDetails?userId=" + sessionStorage.getItem(userIdKey),
			"dataSrc": ""
		},
        "columns": [
            {
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            { "data": "purchaseOrderID" },
			{ "data": "userID.emailAddress" },
            { "data": "orderStatus" },
            { "data": "billingAddress" },
            { "data": "shippingAddress" },
            { "data": "purchaseTotalAmount" },
            { 
				"data": "invoice",
				"render": function(data, type, row, meta){
					if(type === 'display' && data !== null){
						data = '<a href="invoice.html?invoiceId=' + data.invoiceId + '" target="_blank" onclick="return showInvoice(\''+ data.invoiceId +'\')" >#' + data.invoiceId + '</a>';
					}

					return data;
				 }
			}
        ],
        "order": [[1, 'asc']]
    });
   
 
    $('#fullfillmentTB tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
    
 // Add event listener for opening and closing details
    $('#fullfillmentTB tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = t.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data().purchaseProducts) ).show();
            tr.addClass('shown');
        }
    } );
    
    function format ( p ) {
		// `d` is the original data object for the row
		var formatStr = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
				'<thead>' +
						'<tr>' +
							'<th>Product Name</th>'+
							'<th>Unit Price</th>' +
							'<th>Quantity</th>' +
							'<th>Total Amount</th>' +
						'</tr>'+
					'</thead>'+
			'<tbody>';
			for(var i=0; i<p.length; i++){
				formatStr+= '<tr>'+
							'<td>'+p[i].product.productName+'</td>'+
							'<td>'+p[i].product.unitPrice+'</td>'+
							'<td>'+p[i].quantity+'</td>'+
							'<td>'+p[i].totalAmount+'</td>'+
							'</tr>';
			}
			
			formatStr+= '</tbody>' +
		'</table>';
		
		//console.log(formatStr);
		return formatStr;
	}
 
    function compare(a, b){
    	if(a.userID.emailAddress === b.userID.emailAddress && a.billingAddress === b.billingAddress && a.shippingAddress === b.shippingAddress){
    		return true;
    	} else {
    		return false;
    	}
    }
    function generateInvoice(){
		var selectedPurchaseOrders = t.rows('.selected').data();
		var isValid = true;
		for(var i=0; i<selectedPurchaseOrders.length-1; ){
			if(!compare(selectedPurchaseOrders[i], selectedPurchaseOrders[++i])){
				isValid = false;
				break;
			}
		}
		if(!isValid){
			alert("Selected Purchase Order doesn't match with either E-Mail Address, Billing Address, Shipping Address");
			return;
		}
		
		for(var i=0; i<selectedPurchaseOrders.length; i++){
			var selectedPurchaseOrder = selectedPurchaseOrders[i];
			if(selectedPurchaseOrder.invoice != null){
				isValid = false;
				break;
			}
		}
		if(!isValid){
			alert("Some Selected Purchase Order is already fullfilled.");
			return;
		}
		
		var subTotal = $('#subTotal').val();
		var discount = $('#discount').val(); 
		var subTotalLessDiscount = $('#subTotalLessDiscount').val(); 
		var taxRate = $('#taxRate').val(); 
		var totalTax = $('#totalTax').val(); 
		var shippingFee = $('#shippingFee').val(); 
		
		var invoiceDTO = new Object();
		invoiceDTO.subTotal = subTotal;
		invoiceDTO.discount = discount;
		invoiceDTO.subTotalLessDiscount = subTotalLessDiscount;
		invoiceDTO.taxRate = taxRate;
		invoiceDTO.totalTax = totalTax;
		invoiceDTO.shippingFee = shippingFee;
		
		var orders = [];
		for(var i=0; i<selectedPurchaseOrders.length; i++){
			var selectedPurchaseOrder = selectedPurchaseOrders[i];
			selectedPurchaseOrder.invoice = invoiceDTO;
			orders.push(selectedPurchaseOrder);
		}
		
		$.ajax({
   		  url: "purchase/fullfillPurchaseOrder",
   		  type : "POST",
   		  dataType: 'json',
   		  contentType: "application/json",
	      data: JSON.stringify(orders),
   		  success: function(result){
			  alert("Invoice Generated Successfully!!!");
   			  window.location.href = hostURL + "fullfillment.html";
   			}
    	 });
		 
    }
    
    var dialog = $( "#dialog-form" ).dialog({
        autoOpen: false,
        height: 610,
        width: 410,
        modal: true,
        buttons: {
          "Generate Invoice": generateInvoice,
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
      
    $('#openTaxDetails').click(function() {
		var selectedPurchaseOrders = t.rows('.selected').data();
		if(selectedPurchaseOrders.length == 0){
			alert("Please select any Purchase Order");
			return;
		}
		var subTotal = 0;
		for(var i=0; i<selectedPurchaseOrders.length; i++){
			var selectedOrder = selectedPurchaseOrders[i];
			subTotal+=selectedOrder.purchaseTotalAmount;
		}
		$('#subTotal').val(subTotal);
    	dialog.dialog( "open" );
    });
    
     $('#discount').blur(function() {
		 try{
			var discount = parseFloat($('#discount').val());
			var subTotal = $('#subTotal').val();
			var totalLessDiscount = subTotal * ((100-discount)/100);
			$('#subTotalLessDiscount').val(totalLessDiscount.toFixed(2));
		 } catch(err){
			 alert("Wrong Input");
		 }
    });
	
	$('#taxRate').change(function() {
		var taxRate = parseFloat($('#taxRate').val());
		var subTotalLessDiscount = parseFloat($('#subTotalLessDiscount').val());
		var totalTax = subTotalLessDiscount * (taxRate/100);
		$('#totalTax').val(totalTax.toFixed(2));
    });
    
    /*$('#generateInvoice').click( function () {
    	var selectedPurchaseOrders = t.rows('.selected').data();
    	
    	 $.ajax({
   		  url: "purchase/fullfillPurchaseOrder",
   		  type : "POST",
   		  dataType: 'json',
   		  contentType: "application/json",
	      data: JSON.stringify(selectedPurchaseOrders),
   		  success: function(result){
   			  window.location.href = hostURL + "fullfillment.html";
   			}
    	 });
    });*/
    
    $('#back').click( function () {
    	 window.location.href = hostURL + "viewPurchaseOrder.html";
    });
    
} );