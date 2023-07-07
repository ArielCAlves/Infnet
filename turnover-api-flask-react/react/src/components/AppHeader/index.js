import { BellFilled, MailOutlined } from "@ant-design/icons";
import { Badge, Drawer, Image, List, Space, Typography } from "antd";
import { useEffect, useState } from "react";
import { getComments } from "../../API";


function AppHeader(){

    const [comments, setComments] = useState([]);
    
    const [commentsOpen, setCommentsOpen] = useState(false);
    const [notificationsOpen, setNotificationsOpen] = useState(false);

    useEffect(() => {
        getComments().then((res) => {
          setComments(res.comments);
        });              
      }, []);

    return(
        <div className="AppHeader">        

        <Typography.Title className="title">Previsão de Turnover</Typography.Title>
        
        </div>        
    );
}

export default AppHeader;