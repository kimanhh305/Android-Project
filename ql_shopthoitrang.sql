-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 13, 2018 lúc 01:12 PM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ql_shopthoitrang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sodienthoai` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `email`) VALUES
(1, 'Mai Văn Ngọc ', '0987678767 ', 'duylq0011@gmail.com'),
(2, 'Lê Minh Bình', '0909876545', 'binhlm007@gmail.com '),
(3, 'Hoàng Mỹ Tường Uyên', '0902776895', 'uyenmt@gmail.com'),
(4, 'Nghiêm Thúy Vy', '0989098987', 'vynt005@gmail.com '),
(5, 'Nguyễn Đình Quý', '0989098909 ', 'quynd@gmail.com ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisp` varchar(200) NOT NULL,
  `hinhanh` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisp`, `hinhanh`) VALUES
(1, 'Thời Trang Nữ', 'https://sakurafashion.vn/upload/sanpham/4386-top-7-shop-thoi-trang-nu-dep-nhat-tai-dong-thap.jpg'),
(2, 'Thời Trang Nam', 'http://static1.bestie.vn/Mlog/UploadThumbs/201805/660x380_son-tung-m-tp-dien-nhung-thiet-ke-moi-nhat-cua-gucci-20180523001400.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(250) NOT NULL,
  `mota` varchar(10000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `mota`, `idsanpham`) VALUES
(1, 'Áo Sơ Mi Nữ', 150000, 'https://vn-test-11.slatic.net/p/7/ao-so-mi-nu-cao-cap-a001-trang-7034-1667545-ea80e028444a2791ed159fe338f25e39-catalog.jpg_340x340q80.jpg', 'Áo sơ mi cổ đức mang đến cho nàng vẻ đẹp thanh lịch, cổ điển mà không kém phần quyến rũ.\r\n \r\nÁo sơ mi cổ đức cổ điển quyến rũ với phần cổ áo được thiết kế sắc xảo củng form chuẩn sẽ giúp cho các cô nàng sành điệu tự tin diện trong cả tuần làm việc.\r\nSơ mi cổ đức phối đẹp với quần tây, chân váy, Sơ mi + quần jeans, công thức mix đồ vừa đơn giản lại hiệu quả.', 1),
(2, 'Áo sơ mi Nam', 200000, 'https://vn-live-01.slatic.net/original/2f0c70770932775df639141b7a48cb35.jpg', 'Áo sơ mi nam vải lụa nến không nhăn thời trang cao cấp', 2),
(3, 'Áo Khoát Nữ', 300000, 'https://www.thoitrangtichtac.com/productimg/11000/10157/127_Ao_khoac_nu_kaki_phoi_non_mau_reu_b0157.jpg', 'Thời tiết mùa hè khiến các cô gái khi xuống đường luôn cần trang bị cho mình những thiết kế áo khoác nữ đẹp nhằm che chắn cơ thể khỏi những tác hại không tốt từ môi trường. Một chiếc áo khoác có chất liệu tốt, thông thoáng mỗi khi vận động luôn được ưu tiên. Chất liệu kaki, với công dụng tuyệt vời sẽ mang đến cho nàng sự lựa chọn không thể hoàn hảo hơn. Áo có thể diện trong những ngày nắng nóng, những ngày tiết trời trở lạnh, mang đến cho bạn gái vẻ nổi bật và thật thời trang.', 1),
(4, 'Áo Khoát Nữ Đẹp', 450000, 'https://aothunin.com/uploads/shops/2016_10/ao-khoac-nu-kaki-2-lop-in-hinh-de-thuong-mau-den.jpg', 'Áo khoác nữ kaki 2 lớp chất lượng đảm bảo, giá thành rẻ đẹp tại MTTShop, phong cách thời trang nữ tính ấn tượng, style Hàn Quốc trẻ trung bắt mắt', 1),
(5, 'Áo Thun Nữ Xinh Xắn', 150000, 'https://vn-live-02.slatic.net/original/53a098ccfaed7393bbed4bc6bc2ff28f.jpg', 'Áo thun trơn nữ form rộng phong cách hàn quốc vải dày mịn Thời Trang Everest (Trắng)', 0),
(6, 'Đồng Phục Công Sở', 350000, 'https://thoitrangmantis.com/wp-content/uploads/2017/10/dong-phuc-vest-nu-cong-so-mantis-dfdfffdf.jpg', 'Đồng phục công sở áo vest nữ dáng ngắn màu đen sang trọng', 1),
(7, 'Quần Vải Nữ', 300000, 'http://homeshop123.net/profiles/homeshop123net/uploads/attach/1462885573_07171498.jpg', 'Quần vải nữ uniqlo chất vải mềm,nhẹ,rất ít bị phai màu,kiểu dáng gọn nhẹ, tạo cho người mặc cảm giác nhẹ nhàng thoải mái', 1),
(8, 'Quần Short Thể Thao', 150000, 'https://cdn-chiaki.megaads.vn/unsafe/0x900/left/top/smart/filters:quality(75)/https://chiaki.vn/upload/product/2016/03/quan-short-the-thao-cho-nu-chat-lieu-cotton-cao-cap0-18032016114739.png', 'Quần Short Thể Thao Cho Nữ Chất Liệu Cotton Cao Cấp', 1),
(9, 'Váy Đẹp', 450000, 'http://minhshopping.com/userdata/1501/wp-content/uploads/2017/09/banner-2.jpg', 'Khi kết hợp đồ nhiều lớp cùng nhiều trang phục “to bự” trong ngày giá rét hay những ngày nóng bức thường khiến nữ giới thấy khó khăn trong việc chọn ra chiếc váy nữ đẹp vừa thu hút, vừa hữu dụng. Cho dù bạn là người không hứng thú với hầu hết dòng xu thế thời trang hoặc 01 cô nàng thời trang thích điệu, thì chắc chắn bạn đã có các chiếc váy nữ đẹp là vật bất li thân mỗi ngày. Tuy nhiên, khác với những ngày nóng tùy ý chọn lựa kiểu váy mát mẻ, váy dành mặc mùa đông lại không đơn giản như mùa hè. Với “tầng tầng lớp lớp” áo ấm làm ấm sẽ làm cho không ít những cô gái “mất thời gian” để mua được chiếc váy phù hợp với bộ dạng “to sụ” này. Nếu như đây cũng chính là băn khoăn của bạn thì hãy cùng xem gợi ý những kiểu váy theo thời gian“làm nhiều người mê mẩn” giúp bạn thoải mái diện cho bốn mùa nhé.', 1),
(10, 'Áo Dài Việt Nam', 250000, 'http://lamtho.vn/wp-content/uploads/2018/04/ao-dai.jpg', 'Họa tiết nón quạt mang vẻ ngoài không kém phần ấn tượng, hoạt bát cho bạn gái bởi nón quạt thường xuất hiện cùng các bạn học sinh trong những hoạt động văn nghệ truyền thống của trường, trong khi đó với những đặc tính mạnh mẽ và kết nối thì họa tiết điêu khắc hay đan lát cũng sẽ là lựa chọn tối ưu dành cho những bạn nữ tự tin khoe cá tính.', 1),
(11, 'Vest Nam', 500000, 'http://zomaba.com/wp-content/uploads/2018/05/Vest-nam-th%E1%BB%9Di-trang-81.jpg', 'Vest nam  đây là món trang phục mang đến cho phái mạnh vẻ nam tính, thời thượng. Chúng được những ngôi sao nổi tiếng ‘chọn mặt, gửi vàng’ bởi những ưu điểm nổi bật mà không xu hướng mới mẻ nào thay thế được.\r\n\r\nChất lượng tuyệt hảo trong từng sợi chỉ là những gì cần biết đến đầu tiên về Zomaba. Những bộ vest của hãng được làm nên từ những sợi vải tốt nhất của những thợ dệt lành nghề nhất và nổi tiếng nhất trên thế giới. Như Canali, các bộ vest Zomaba góp mặt trong danh sách này nhờ sự hoàn hảo trong từng chi tiết. Với thiết kế thanh lịch và cổ điển, các quí ông có thể tự tin vận lên mình bộ vest  của Zomaba và tham dự bất cứ sự kiện hay dịp lễ nào.', 2),
(12, 'Áo Thun Trơn', 200000, 'https://dotoza.com/wp-content/uploads/2018/04/%C3%A1o-thun-nam-H%C3%A0n-Qu%E1%BB%91c.png', 'Chất liệu thun mềm mại co giãn tốt , thoáng mát\r\nThiết kế thời trang phù hợp xu hướng hiện nay\r\nKiểu dáng đa phong cách\r\nĐường may tinh tế sắc sảo\r\nÁo thun được thiết kế vể đẹp trẻ trung năng động nhưng không kém phần mạnh mẽ.\r\nÁo được thiết kế đẹp, chuẩn form, đường may sắc xảo\r\nVải cotton dày, mịn, thấm hút mồ hôi tạo sự thoải mái khi mặc!\r\nDễ dàng phối trang phục , thích hợp đi chơi đi làm đi dạo phố\r\nThích hợp cho sự kết hợp vứi quần jean, sọt, kaki…!', 2),
(13, 'Đồng Phục Công Sở Nam', 550000, 'http://vestnguyen.com/images/upload/dpcongso/mau-dong-phuc-cong-so-duoc-uu-chuong-nhat.jpg', 'Với đồng phục Veston thì đây là sản phẩm cao cấp và đắt tiền và trong tâm lý người mặc thì họ luôn nghĩ đó là trang phục được mặc để phục vụ cho những người có tiền hay cho sự kiện, nghi lễ trang trọng nhưng gần đây vest nam đã được biến tấu để phù hợp và thân thiện với người mặc hơn', 2),
(14, 'Jumpsuit', 280000, 'https://img.ltwebstatic.com/origin/images2_pi/2018/05/29/15275745943379401798_im_405x552.jpg', 'Jumpsuits Casual Đồng Bằng Knot Đỏ, phong cách casual dành cho mùa hè', 1),
(15, 'Legging', 186000, 'https://img.ltwebstatic.com/origin/images2_pi/2018/08/21/15348364413670979540_im_405x552.jpg', 'Xà Cạp Thể Thao Tương Phản Mesh Xám 95% Polyester, 5% Spandex Thể thao', 1),
(16, 'Quần ống rộng', 210000, 'https://img.ltwebstatic.com/origin/images2_pi/2018/05/14/15263004053738316883_im_405x552.jpg', 'Quần Tao Nhã Đồng Bằng Nút Khaki vòng eo cao chất liệu 97% Bông, 3% Spandex', 1),
(17, 'Áo kiểu cổ chữ V cá tính AA', 280000, 'https://file.yes24.vn/Upload/ProductImage/anhduong201605/1914646_L.jpg', '- Chất liệu: Chiffon Lụa.\r\n- Thiết kế thời trang trẻ trung.\r\n- Đường may sắc nét, màu sắc thanh lịch nữ tính.\r\n- Dễ dàng phối hợp cùng phụ kiện cho set đồ hoàn hảo.', 1),
(18, 'Đầm tay ngắn De Vani thắt eo RIBU DRESS_DEN', 219000, 'https://file.yes24.vn/Upload/ProductImage/devani/1944893_L.jpg', '- Chất liệu : cat.\r\n- Kích thước : Freesize 45-55kg cao 1,5-1,65m ,ngực dưới 90cm , eo dưới 72 cm.\r\n- Thiết kế thời trang trẻ trung.\r\n- Kiểu dáng nhẹ nhàng nữ tính.', 1),
(19, 'Áo kiểu thắt nơ ngọt ngào', 280000, 'https://file.yes24.vn/Upload/ProductImage/anhduong201605/1914647_L.jpg', '- Chất liệu: Chiffon Lụa.\r\n- Thiết kế thời trang trẻ trung.\r\n- Đường may sắc nét, màu sắc thanh lịch nữ tính.\r\n- Dễ dàng phối hợp cùng phụ kiện cho set đồ hoàn hảo.', 1),
(20, 'Quần baggy đen sang trọng ', 380000, 'https://file.yes24.vn/Upload/ProductImage/anhduong201605/1923954_L.jpg', '- Chất liệu cao cấp, bền đẹp.\r\n- Kiểu dáng thời trang trẻ trung.\r\n- Đường may sắc nét tỉ mỉ từng chi tiết.', 1),
(21, 'Đầm thời trang Dany', 450000, 'https://file.yes24.vn/Upload/ProductImage/vhh2017/1937543_L.jpg', '- Kiểu dáng thời trang trẻ trung.\r\n- Phong cách tự tin duyên dáng đầy nữ tính.\r\n- Sản phẩm dễ phối cùng phụ kiện cho set đồ hoàn hảo.', 1),
(22, 'Áo sơ mi kiểu đai thắt eo thời trang', 420000, 'https://file.yes24.vn/Upload/ProductImage/anhduong201605/1956061_L.jpg', '- Chất liệu chiffon cao cấp.\r\n- Đường may tỉ mỉ, tinh tế.\r\n- Kiểu dáng thời trang, trẻ trung, hiện đại.', 1),
(23, 'Đầm tay ngắn cổ V viền bèo đỏ ', 239000, 'https://file.yes24.vn/Upload/ProductImage/devani/1937371_L.jpg', '- Chất liệu: kate lụa.\r\n- Thiết kế thời trang trẻ trung.\r\n- Kiểu dáng nhẹ nhàng nữ tính.', 1),
(24, 'JUMP De Vani LAUREN JUMP', 209000, 'https://file.yes24.vn/Upload/ProductImage/devani/1925188_L.jpg', '- Chất liệu: kate lụa.\r\n- Thiết kế thời trang trẻ trung.\r\n- Kiểu dáng nhẹ nhàng nữ tính.', 1),
(25, 'Áo kiểu đen tay ngọc trai', 280000, 'https://file.yes24.vn/Upload/ProductImage/anhduong201605/1906001_L.jpg', '- Chất liệu: Lụa cao cấp.\r\n- Thiết kế thời trang trẻ trung.\r\n- Đường may sắc nét, màu sắc thanh lịch nữ tính.\r\n- Dễ dàng phối hợp cùng phụ kiện cho set đồ hoàn hảo.', 1),
(26, 'Áo Thun Noonc', 699000, 'https://image.robins.vn/uv6Diwd-Cbhywq9QOG-maCW8fzw=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/overlay/mockup-chinhhang-final.png', 'Hiện đại cùng áo thun nam thiết kế bởi MANGO Man. Áo có thiết kế đơn giản cùng màu sắc trơn dễ dàng phối cùng nhiều phong cách trang phục khác nhau.\r\n\r\n- Chất liệu 65% linen,35% cotton\r\n- Cổ tròn\r\n- Tay dài\r\n- Không may lót', 2),
(27, 'Quần Florence', 1049000, 'https://image.robins.vn/uv6Diwd-Cbhywq9QOG-maCW8fzw=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/overlay/mockup-chinhhang-final.png', 'Cho vẻ ngoài thêm lịch lãm cùng quần kiểu nam thiết kế bởi MANGO Man. Quần với form suông cùng màu sắc trơn dễ phối là item không thể thiếu trong tủ đồ của các chàng trai.\r\n\r\n- Chất liệu 69% polyester,29% viscose,2% elastane\r\n- Mặt trước quần may nút gài phối khoá kéo\r\n- 2 túi bên hông\r\n- Không may lót', 2),
(28, 'Áo Khoác Viền Túi Đánh Bông', 170240, 'https://image.robins.vn/IMaAat7EwysgCh-Dy11wNQUe1p4=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/p/thoi-trang-phuc-an-4656-027118-1.jpg', 'Trẻ trung và năng động với áo khoác kiểu thiết kế bởi Phúc An. Áo có màu sắc tươi tắn nổi bật tạo điểm nhấn cho vẻ ngoài của bạn.\r\n\r\n- Chất liệu cotton pha\r\n- Áo có nón\r\n- Tay dài\r\n- Mặt trước áo may khóa kéo\r\n- May túi 2 bên hông\r\n- Không may lót', 2),
(29, 'Áo Sơ Mi Nam LEE', 1090000, 'https://image.robins.vn/3r8fp9dyWsCoCArjCZbP-i1jxIQ=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/p/lee-3831-462208-1.jpg', 'Mix match cùng nhiều phong cách khác nhau với áo sơ mi jeans thiết kế bởi Lee. Áo có thiết kế đơn giản hiện đại sẽ là item hoàn hảo để bạn tự tin bắt đầu ngày mới.\r\n\r\n- Chất liệu cotton pha\r\n- Cổ lật\r\n- Tay dài\r\n- Mặt trước áo may hàng nút gài\r\n- Không may lót', 2),
(30, 'Quần Short Thun Nam Dáng Đứng', 98000, 'https://image.robins.vn/v1G9MQV6FSH9JHdZ9oXhg8RefBk=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/p/thoi-trang-phuc-an-6085-932285-1.jpg', 'Bo lưng thun bản rộng mặc rất dễ chịu\r\nChất liệu thun da cá mềm và thoáng\r\nKhông xù lông, không ra màu\r\nCó 3 size:\r\nM: nam nặng dưới 60kg, cao dưới 1m70\r\nL: nam nặng dưới 70kg, cao dưới 1m75\r\nXL: nam nặng dưới 85kg, cao dưới 1m85', 2),
(31, 'Áo Sơ Mi Play2', 899000, 'https://image.robins.vn/uv6Diwd-Cbhywq9QOG-maCW8fzw=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/overlay/mockup-chinhhang-final.png', 'Thêm phần thanh lịch và nam tính với áo sơ mi từ thương hiệu MANGO Man. Thiết kế áo kiểu dáng cổ điển tăng thêm tính đơn giản, dễ mặc.\r\n\r\n- Chất liệu 100% cotton\r\n- Áo cổ lật\r\n- Tay dài\r\n- Mặt trước áo may hàng nút gài\r\n- Không may lót', 2),
(32, 'Aó sơ mi nam Jeanswest', 719000, 'https://image.robins.vn/uv6Diwd-Cbhywq9QOG-maCW8fzw=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/overlay/mockup-chinhhang-final.png', 'Áo thun nam thương hiệu JeansWest mạnh mẽ,nam tính và sang trọng dành cho phái mạnh.\r\n- Chất liệu: 100% cotton', 2),
(33, 'Áo Thun Nam Body Cổ Tròn Trắng ', 239000, 'https://image.robins.vn/tw6HHUUv4Lzmm7JwZbA-ZnYs5yg=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/p/macaron-1651-841755-1.jpg', '', 2),
(34, 'Áo Khoác Jean Thời Trang', 520000, 'https://image.robins.vn/HtcYQ4srrBqyueRGPpYxnsvzh6Y=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/p/thoi-trang-duy-phat-5327-163186-1.jpg', '- Áo khoác jean chất vải cao cấp , mịn, không nhăn , thoải mái và rất thoáng mát. Form áo ôm body. Mẫu áo khoác trẻ trơn trẻ trung, đơn giản dễ phối đồ. Phù hợp cho các bạn nam mặc đi chơi hay đi làm đều đẹp và lịch sự.', 2),
(35, 'Quần Short Nam     \r\nUrban N&T', 250000, 'https://image.robins.vn/eiP6pQHiYwo5TmoYJ9R2Jc8aq9A=/fit-in/236x345/filters:quality(90):fill(ffffff)/http://static-catalog.robins.vn/p/urban-n-t-5150-168207-1.jpg', '', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
