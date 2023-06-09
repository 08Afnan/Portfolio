<?php
/**
 * Afnan Islam
 * Id: 041082073
 * Description: In this page php reads the form variables and isset function being used to set the value of variables
 * Assignment 2
 */




// Include employeeDAO file
require_once('./dao/customerDAO.php');
$customerDAO = new customerDAO(); 

// Check existence of id parameter before processing further
if(isset($_GET["id"]) && !empty(trim($_GET["id"]))){
    // Get URL parameter
    $id =  trim($_GET["id"]);
    $customer = $customerDAO->getCustomer($id);
            
    if($customer){
        // Retrieve individual field value
        $name = $customer->getName();
        $address = $customer->getAddress();
        $payment = $customer->getPayment();
        $service = $customer->getService();
    } else{
        // URL doesn't contain valid id. Redirect to error page
        header("location: error.php");
        exit();
    }
} else{
    // URL doesn't contain id parameter. Redirect to error page
    header("location: error.php");
    exit();
} 

// Close connection
$customerDAO->getMysqli()->close();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Record</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .wrapper{
            width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="mt-5 mb-3">View Record</h1>
                    <div class="form-group">
                        <label>Name</label>
                        <p><b><?php echo $name; ?></b></p>
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <p><b><?php echo $address; ?></b></p>
                    </div>
                    <div class="form-group">
                        <label>Payment</label>
                        <p><b><?php echo $payment; ?></b></p>
                    </div>
                    <div class="form-group">
                        <label>Service</label>
                        <p><b><?php echo $service; ?></b></p>
                    </div>
                    <p><a href="index.php" class="btn btn-primary">Back</a></p>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>