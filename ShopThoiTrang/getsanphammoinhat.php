<?php
	include "ketnoi.php";
	$query = "SELECT * from sanpham ORDER BY ID DESC LIMIT 6";
	$data = mysqli_query($conn,$query);
	$mangsanphammoinhat = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangsanphammoinhat, new Sanphammoinhat(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhanhsanpham'],
			$row['mota'],
			$row['idsanpham']));
	}
	echo json_encode($mangsanphammoinhat);
	class Sanphammoinhat{
		function Sanphammoinhat($id,$tensanpham,$giasanpham,$hinhanhsanpham,$mota,$idsanpham){
			$this->id=$id;
			$this->tensanpham=$tensanpham;
			$this->giasanpham=$giasanpham;
			$this->hinhanhsanpham=$hinhanhsanpham;
			$this->mota=$mota;
			$this->idsanpham=$idsanpham;
		}
	}
?>