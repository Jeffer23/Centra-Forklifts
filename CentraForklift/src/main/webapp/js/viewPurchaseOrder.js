
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
	
	if(sessionStorage.getItem(userRoleKey) == 'dealer'){
		$("#approve").hide();
		$("#fullfillmentLink").hide();
		$("#syncWithFerret").hide();
	}
	
    var t = $('#invoiceTB').DataTable({
		"ajax": {
			"url": "purchase/getInvoiceTabDetails?userId=" + sessionStorage.getItem(userIdKey),
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
			{ "data": "userID.companyName" },
			{ "data": "userID.emailAddress" },
			{ 
				"data": "orderDate",
				"render": function(data, type, row, meta){
					if(type === 'display' && data !== null){
						data = data.substring(0, 10);
					}
					return data;
				 }
			},
            { "data": "purchaseTotalAmount" },
            { "data": "orderStatus" }
        ],
        "order": [[1, 'asc']]
    });
 
    $('#invoiceTB tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
	
	// Add event listener for opening and closing details
    $('#invoiceTB tbody').on('click', 'td.details-control', function () {
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
 
    $('#approve').click( function () {
    	var selectedPurchaseOrders = t.rows('.selected').data();
    	var totalSelectedOrders = t.rows('.selected').data().length;
    	var purchaseOrderIds = [];
    	for(var i=0; i<totalSelectedOrders ; i++){
    		var purchaseOrder = selectedPurchaseOrders[i];
    		purchaseOrderIds.push(purchaseOrder.purchaseOrderID);
    	}
    	 $.ajax({
   		  url: "purchase/approvePurchaseOrder",
   		  type : "POST",
   		  dataType: 'json',
   		  contentType: "application/json",
	      data: JSON.stringify(purchaseOrderIds),
   		  success: function(result){
	   			alert("Purchase with Order Ids : " + purchaseOrderIds + "approved!!")
   			window.location.href = hostURL + "viewPurchaseOrder.html";
   			}
    	 });
    });
    
    $('#addPurchaseOrder').click( function () {
    	window.location.href = hostURL + "addPurchase.html";
    });
    
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
	
	 $('#syncWithFerret').click( function () {
		 $.ajax({
			  url: "purchase/syncWithFerretApp",
			  success: function(result){
				  alert("Sync Successfull!!!");
				  window.location.href = hostURL + "viewPurchaseOrder.html";
				},
				 error: function(result){
					 alert("Error is Syncing with Ferret Application. Please try again after sometime or contact Admin.");
				}
			 });
	 });
	
} );