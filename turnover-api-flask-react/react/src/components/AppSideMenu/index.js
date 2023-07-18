import {
    AppstoreOutlined,
    ShopOutlined,
    ShoppingCartOutlined,
    UserOutlined,
  } from "@ant-design/icons";
  import { Menu } from "antd";
  import { useEffect, useState } from "react";
  import { useLocation, useNavigate } from "react-router-dom";
  
function AppSideMenu() {
    const location = useLocation();
    const [selectedKeys, setSelectedKeys] = useState("/");

    const navigate = useNavigate();
  
    useEffect(() => {
      const pathName = location.pathname;
      setSelectedKeys(pathName);
    }, [location.pathname]); 
    
    
    return (
      <div className="AppSideMenu">
        <Menu
          className="SideMenuVertical"
          mode="vertical"
          onClick={(item) => {           
            navigate(item.key);
          }}
          selectedKeys={[selectedKeys]}
          items={[
            {
              label: "Home",
              icon: <AppstoreOutlined />,
              key: "/",
            },
            {
              label: "Turnover",
              key: "/turnover",
              icon: <ShopOutlined />,
            },            
            {
              label: "Usuários",
              key: "/usuarios",
              icon: <UserOutlined />,
            },
            {
              label: "Configurações",
              key: "/configuracoes",
              icon: <ShoppingCartOutlined />,
            },
          ]}
        ></Menu>
      </div>
    );
  }
export default AppSideMenu;
  