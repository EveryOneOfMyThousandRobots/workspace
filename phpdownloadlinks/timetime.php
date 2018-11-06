<?php
  $var = "2018-01-01 00:24:00";
  $now = date('Y-m-d H:i:s');
  echo $var."<br .>".$now;
  if ($var < $now) {
    echo "less";
  } else {
    echo "more";
  }
  

?>