<? /**********************************************************
 * function resizejpeg:
 *
 *  = creates a resized image based on the max width
 *    specified as well as generates a thumbnail from
 *    a rectangle cut from the middle of the image.
 *
 *    @dir    = directory image is stored in
 *    @newdir = directory new image will be stored in
 *    @img    = the image name
 *    @max_w  = the max width of the resized image
 *    @max_h  = the max height of the resized image
 *    @th_w   = the width of the thumbnail
 *    @th_h   = the height of the thumbnail
 *
 **********************************************************/

function ConvertBMP2GD($src, $dest = false) {
if(!($src_f = fopen($src, "rb"))) {
return false;
}
if(!($dest_f = fopen($dest, "wb"))) {
return false;
}
$header = unpack("vtype/Vsize/v2reserved/Voffset", fread($src_f,
14));
$info = unpack("Vsize/Vwidth/Vheight/vplanes/vbits/Vcompression/Vimagesize/Vxres/Vyres/Vncolor/Vimportant",
fread($src_f, 40));

extract($info);
extract($header);

if($type != 0x4D42) { // signature "BM"
return false;
}

$palette_size = $offset - 54;
$ncolor = $palette_size / 4;
$gd_header = "";
// true-color vs. palette
$gd_header .= ($palette_size == 0) ? "\xFF\xFE" : "\xFF\xFF";
$gd_header .= pack("n2", $width, $height);
$gd_header .= ($palette_size == 0) ? "\x01" : "\x00";
if($palette_size) {
$gd_header .= pack("n", $ncolor);
}
// no transparency
$gd_header .= "\xFF\xFF\xFF\xFF";

fwrite($dest_f, $gd_header);

if($palette_size) {
$palette = fread($src_f, $palette_size);
$gd_palette = "";
$j = 0;
while($j < $palette_size) {
$b = $palette{$j++};
$g = $palette{$j++};
$r = $palette{$j++};
$a = $palette{$j++};
$gd_palette .= "$r$g$b$a";
}
$gd_palette .= str_repeat("\x00\x00\x00\x00", 256 - $ncolor);
fwrite($dest_f, $gd_palette);
}

$scan_line_size = (($bits * $width) + 7) >> 3;
$scan_line_align = ($scan_line_size & 0x03) ? 4 - ($scan_line_size &
0x03) : 0;

for($i = 0, $l = $height - 1; $i < $height; $i++, $l--) {
// BMP stores scan lines starting from bottom
fseek($src_f, $offset + (($scan_line_size + $scan_line_align) *
$l));
$scan_line = fread($src_f, $scan_line_size);
if($bits == 24) {
$gd_scan_line = "";
$j = 0;
while($j < $scan_line_size) {
$b = $scan_line{$j++};
$g = $scan_line{$j++};
$r = $scan_line{$j++};
$gd_scan_line .= "\x00$r$g$b";
}
}
else if($bits == 8) {
$gd_scan_line = $scan_line;
}
else if($bits == 4) {
$gd_scan_line = "";
$j = 0;
while($j < $scan_line_size) {
$byte = ord($scan_line{$j++});
$p1 = chr($byte >> 4);
$p2 = chr($byte & 0x0F);
$gd_scan_line .= "$p1$p2";
}
$gd_scan_line = substr($gd_scan_line, 0, $width);
}
else if($bits == 1) {
$gd_scan_line = "";
$j = 0;
while($j < $scan_line_size) {
$byte = ord($scan_line{$j++});
$p1 = chr((int) (($byte & 0x80) != 0));
$p2 = chr((int) (($byte & 0x40) != 0));
$p3 = chr((int) (($byte & 0x20) != 0));
$p4 = chr((int) (($byte & 0x10) != 0));
$p5 = chr((int) (($byte & 0x08) != 0));
$p6 = chr((int) (($byte & 0x04) != 0));
$p7 = chr((int) (($byte & 0x02) != 0));
$p8 = chr((int) (($byte & 0x01) != 0));
$gd_scan_line .= "$p1$p2$p3$p4$p5$p6$p7$p8";
}
$gd_scan_line = substr($gd_scan_line, 0, $width);
}

fwrite($dest_f, $gd_scan_line);
}
fclose($src_f);
fclose($dest_f);
return true;
}

function imagecreatefrombmp($filename) {
$tmp_name = tempnam("/tmp", "GD");
if(ConvertBMP2GD($filename, $tmp_name)) {
$img = imagecreatefromgd($tmp_name);
unlink($tmp_name);
return $img;
}
return false;
}




function resizejpeg($dir, $newdir, $img,$newimg, $max_w, $max_h, $th_w, $th_h)
{
    // set destination directory
    if (!$newdir) $newdir = $dir;

    // get original images width and height
    list($or_w, $or_h, $or_t) = getimagesize($dir.$img);

    // make sure image is a jpeg
    if ($or_t == 2) {
    
        // obtain the image's ratio
        $ratio = ($or_h / $or_w);

        // original image
        $or_image = imagecreatefromjpeg($dir.$img);

        // resize image?
        if ($or_w > $max_w || $or_h > $max_h) {

            // resize by height, then width (height dominant)
            if ($max_h < $max_w) {
                $rs_h = $max_h;
                $rs_w = $rs_h / $ratio;
            }
            // resize by width, then height (width dominant)
            else {
                $rs_w = $max_w;
                $rs_h = $ratio * $rs_w;
            }

            // copy old image to new image
            $rs_image = imagecreatetruecolor($rs_w, $rs_h);
            imagecopyresampled($rs_image, $or_image, 0, 0, 0, 0, $rs_w, $rs_h, $or_w, $or_h);
        }
        // image requires no resizing
        else {
            $rs_w = $or_w;
            $rs_h = $or_h;

            $rs_image = $or_image;
        }

        // generate resized image
        imagejpeg($rs_image, $newdir.$img, 100);

        $th_image = imagecreatetruecolor($th_w, $th_h);

        // cut out a rectangle from the resized image and store in thumbnail
        $new_w = (($rs_w / 2) - ($th_w / 2));
        $new_h = (($rs_h / 2) - ($th_h / 2));

        imagecopyresized($th_image, $rs_image, 0, 0, $new_w, $new_h, $rs_w, $rs_h, $rs_w, $rs_h);

        // generate thumbnail
        imagejpeg($th_image, $newdir.$newimg, 100);

        return true;
    } 

    // Image type was not jpeg!
    else {
        return false;
    }
}




function Resize2jpeg($Image,$NewImage,$MaxWidth,$MaxHeight,$Quality) {
  list($ImageWidth,$ImageHeight,$TypeCode)=getimagesize($Image);
  $ImageType=($TypeCode==1?"gif":
  			($TypeCode==6?"bmp":
  			 ($TypeCode==2?"jpeg":
             ($TypeCode==3?"png":FALSE))));
			 
  $CreateFunction="imagecreatefrom".$ImageType;
  $OutputFunction="imagejpeg";
  if ($ImageType) {
    $Ratio=($ImageHeight/$ImageWidth);
    $ImageSource=$CreateFunction($Dir.$Image);
    if ($ImageWidth > $MaxWidth || $ImageHeight > $MaxHeight) {
      if ($ImageWidth > $MaxWidth) {
         $ResizedWidth=$MaxWidth;
         $ResizedHeight=$ResizedWidth*$Ratio;
      }
      else {
        $ResizedWidth=$ImageWidth;
        $ResizedHeight=$ImageHeight;
      }        
      if ($ResizedHeight > $MaxHeight) {
        $ResizedHeight=$MaxHeight;
        $ResizedWidth=$ResizedHeight/$Ratio;
      }       
      $ResizedImage=imagecreatetruecolor($ResizedWidth,$ResizedHeight);
      imagecopyresampled($ResizedImage,$ImageSource,0,0,0,0,$ResizedWidth,
                         $ResizedHeight,$ImageWidth,$ImageHeight);
    } 
    else {
      $ResizedWidth=$ImageWidth;
      $ResizedHeight=$ImageHeight;      
      $ResizedImage=$ImageSource;
    }    
	imagedestroy($ImageSource);		
    $OutputFunction($ResizedImage,$NewImage,$Quality);
	imagedestroy($ResizedImage);			

    return true;
  }    
  else
    return false;
}	



	
?>