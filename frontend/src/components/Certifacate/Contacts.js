import React from "react";
import { Alert, Image } from "react-bootstrap";

const Contacts = () => {
  return (
    <div>
      <Alert key={"dark"} variant={"dark"}>
        <i>
          <b>Контактная информация</b>
        </i>
        <br></br>
        Email: iso_certificate@info.by
        <br></br>
        МТС: +375 (33) 347-56-28
        <br></br>
        А1: +375 (29) 134-59-63
      </Alert>
      <div style={{ display: "block", padding: 30 }}>
        <Image
          src="http://iso-management.com/wp-content/uploads/2013/10/iso-logo.png"
          style={{
            width: "1000px",
            height: "500px",
            objectPosition: "center top",
          }}
          rounded
        />
      </div>
    </div>
  );
};

export default Contacts;
