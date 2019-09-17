
$(document).ready(function() {
		
	function getQueryStringValue (key) {  
		  return decodeURIComponent(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + encodeURIComponent(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));  
		}  

	var invoiceId = getQueryStringValue(invoiceIdKey);
	
	$.ajax({
		  url: "purchase/getInvoiceDetails?invoiceId=" + invoiceId,
		  success: function(result){
			  console.log(result);
			  for(var i=0; i<result.length ; i++){
				  var order = result[i];
				  var name = order.userID.lastName + ", " + order.userID.firstName; 
				  $("#name").text(name);
				  var userAddress = order.userID.address;
				  $("#userAddress").text(userAddress);
				  $("#userEmail").attr("href", "mailto:" + order.userID.emailAddress);
				  $("#userEmail").text(order.userID.emailAddress);
				$("#billToAddress").text(order.billingAddress);
				$("#shipToAddress").text(order.shippingAddress);
				$("#invoiceId").text(order.invoice.invoiceId);
				$("#invoiceDate").text(order.invoice.invoiceDate.substring(0, 10));
				$("#dueDate").text(order.invoice.dueDate.substring(0, 10));
				var tableRows = "";
				for(var j=0; j<order.purchaseProducts.length; j++){
					var product =order.purchaseProducts[j];
					tableRows+='<tr>' +
						'<td class="desc"><h3>Purchase Order : #'+ order.purchaseOrderID +'</h3>' + product.product.productName +'</td>' +
						'<td class="qty">' + product.quantity + '</td>' +
						'<td class="unit">' + product.product.unitPrice + '</td>' +
						'<td class="total">' + product.totalAmount + '</td>' +
					'</tr>';
				}
				$('#ordersTable tr:last').after(tableRows);
				$("#subTotal").text(order.invoice.subTotal);
				$("#discount").text(order.invoice.discount);
				$("#subTotalLessDiscount").text(order.invoice.subTotalLessDiscount);
				$("#taxRate").text(order.invoice.taxRate);
				$("#totalTax").text(order.invoice.totalTax);
				$("#shippingFee").text(order.invoice.shippingFee);
				$("#balanceDue").text(order.invoice.balanceDue);
				
			  }
		}
	  });
	
	
} );