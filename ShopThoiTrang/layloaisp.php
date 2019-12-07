<?php
	include "ketnoi.php";
	$query = "SELECT * from loaisanpham";
	$data=mysqli_query($conn,$query);
	$mangloaisp = array();
	while ($row= mysqli_fetch_assoc($data)) {
		array_push($mangloaisp, new Loaisp(
			$row['id'],
			$row['tenloaisp'],
			$row['hinhanh']));
	}
	echo json_encode($mangloaisp);
	class Loaisp{
		function Loaisp($id,$tenloaisp,$hinhanhloaisp){
			$this->id=$id;
			$this->tenloaisp=$tenloaisp;
			$this->hinhanh=$hinhanhloaisp;
		}
	}
?>