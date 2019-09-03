
$(document).ready(function() {
    var t = $('#invoiceTB').DataTable();
    var counter = 1;
 
    
    $('#addPurchaseOrder').on( 'click', function () {
        t.row.add( [
            counter +'.1',
            counter +'.2',
            counter +'.3',
            counter +'.4',
            counter +'.5',
            counter +'.6',
            counter +'.7'
        ] ).draw( false );
 
        counter++;
    } );
 
    $('#invoiceTB tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
 
    $('#approve').click( function () {
    	var selectedPurchaseOrders = t.rows('.selected').data();
    	var totalSelectedOrders = t.rows('.selected').data().length;
    	var purchaseOrderIds = [];
    	for(var i=0; i<totalSelectedOrders ; i++){
    		var purchaseOrder = selectedPurchaseOrders[i];
    		purchaseOrderIds.push(purchaseOrder[2]);
    	}
    	 $.ajax({
   		  url: "purchase/approvePurchaseOrder",
   		  type : "POST",
   		  dataType: 'json',
   		  contentType: "application/json",
	      data: JSON.stringify(purchaseOrderIds),
   		  success: function(result){
	   			$.ajax({
	   			  url: "purchase/getInvoiceTabDetails?userId=" + loggedInUserID,
	   			  success: function(result){
	   				result.forEach(function(value){
	   					 t.row.add( [
	   				            value.orderDate,
	   				            value.purchaseOrderID,
	   				            value.purchaseOrderID,
	   				            value.quantity,
	   				            value.unitPrice,
	   				            value.totalPrice,
	   				            value.orderStatus
	   				        ] ).draw( false );	
	   				});
	   			}
	   		  });
   			}
    	 });
    });
    
    // Ajax call to get the PurchaseOrders
    $.ajax({
		  url: "purchase/getInvoiceTabDetails?userId=" + loggedInUserID,
		  success: function(result){
			result.forEach(function(value){
				 t.row.add( [
			            value.orderDate,
			            value.purchaseOrderID,
			            value.purchaseOrderID,
			            value.quantity,
			            value.unitPrice,
			            value.totalPrice,
			            value.orderStatus
			        ] ).draw( false );	
			});
		}
	  });
} );