<?php
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
  if (isset($_GET['file']) && isset($_GET['auth'])) {
    $request_name = $_GET['file'];
    $auth = $_GET['auth'];
    $local_file = "C:\\Users\\sam.PROPER\\workspace\\phpdownloadlinks\\files\\$request_name";
    $handle = fopen($local_file, "r");
    if ($handle) {
      $download = fgets($handle); //get the name
      $download = str_replace(array("\n", "\r"), '', $download);
      $file_auth =fgets($handle); //line 2 is the auth string
      $file_auth = str_replace(array("\n", "\r"), '', $file_auth);
      fclose($handle);
      
      if ($download && $file_auth) {
        echo "$auth $file_auth";
        if ("$auth" == $file_auth) {
          ob_start();
          $mm_type = "application.octet-stream";
          $download_name = "download.something";
          header("Cache-Control: public, must-revalidate");
          header("Pragma: no-cache");
          header("Contect-Type: ". $mm_type);
          header("Content-Length: " . (string)(filesize($download)));
          header("Content-Disposition: attachment;filename=\"".$download_name."\"");
          header("Content-Transfer-Encoding: binary\n");
          ob_end_clean();
          readfile($download);
          
          
          
        } else {
          echo "auth failed";
        }
      }
    }

  }

} else {
  echo $_SERVER['REQUEST_METHOD'];
}
?>