<?php
	include = "ketnoi.php";
	$page = $_GET['page'];
	$idsp = 1;
	$space = 5;
	$limit = ($page - 1) * $space;
	$mangsanpham = array();
	$query = "SELECT * FROM sanpham where idsanpham = $idsp LIMIT $limit,$space";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangsanpham, new Sanpham(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhanhsanpham'],
			$row['mota'],
			$row['idsanpham']));
	}
	echo json_encode($mangsanpham);
	class Sanpham{
		function Sanpham($id,$tensanpham,$giasanpham,$hinhanhsanpham,$mota,$idsanpham){
			$this->id=$id;
			$this->tensanpham=$tensanpham;
			$this->giasanpham=$giasanpham;
			$this->hinhanhsanpham=$hinhanhsanpham;
			$this->mota=$mota;
			$this->idsanpham=$idsanpham;
		}
	}
?>