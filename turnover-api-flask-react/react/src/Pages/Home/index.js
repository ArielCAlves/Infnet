import {
  DollarCircleOutlined,
  ShoppingCartOutlined,
  ShoppingOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Card, Space, Statistic, Table, Typography } from "antd";
import { useEffect, useState } from "react";
import { getUsuarios, getTurnover, getReceita } from "../../API";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
  LineController,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar, Line } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
  LineController,
  BarElement,
  Title,
  Tooltip,
  Legend
);

function Home() {  
  const [turnover, setTurnover] = useState(0);
  const [usuarios, setUsuarios] = useState(0);
  const [receita, setReceita] = useState(0);

  useEffect(() => {    
    getTurnover().then((res) => {
      setTurnover(res.total);
    });
    getUsuarios().then((res) => {
      setUsuarios(res.total);
    });
  }, []);

  return (
    <Space size={20} direction="vertical">      
      <Space direction="horizontal">        
        <HomeCard
          icon={
            <ShoppingOutlined
              style={{
                color: "blue",
                backgroundColor: "rgba(0,0,255,0.25)",
                borderRadius: 20,
                fontSize: 24,
                padding: 8,
              }}
            />
          }
          title={"Turnover"}
          value={turnover}
        />
        <HomeCard
          icon={
            <UserOutlined
              style={{
                color: "purple",
                backgroundColor: "rgba(0,255,255,0.25)",
                borderRadius: 20,
                fontSize: 24,
                padding: 8,
              }}
            />
          }
          title={"Usuarios"}
          value={usuarios}
        />
        <HomeCard
          icon={
            <DollarCircleOutlined
              style={{
                color: "blue",
                backgroundColor: "rgba(255,0,0,0.15)",
                borderRadius: 20,
                fontSize: 24,
                padding: 8,
              }}
            />
          }
          title={"Receita"}
          value={receita}
        />
      </Space>     
      <div>        
        <Conteudo />        
      </div>      
      <Space>   
        <HomeChart />    
      </Space>   
    </Space>
  );
}

function HomeCard({ title, value, icon }) {
  return (
    <Card>
      <Space direction="horizontal">
        {icon}
        <Statistic title={title} value={value} />
      </Space>
    </Card>
  );
}


function Conteudo() {
  
  return (  
    <div className="content">
      <section className="box">        
        <img src="https://northyorkshireccg.nhs.uk/wp-content/uploads/2021/02/150x150.png" alt="Foto do Turnover"></img>
        <span className="texto">	
          <h2>O que é o Turnover?</h2>						
          <p>Turnover é...</p>	
          <p>...</p>	         						
        </span>		     
      </section>	

      <section className="box">       
          <img src="https://northyorkshireccg.nhs.uk/wp-content/uploads/2021/02/150x150.png" alt="Foto do Impacto"></img>
          <span className="texto">	
            <h2>Quais são os impactos para a empresa?</h2>						
            <p>Os impactos são...</p>			
            <p>...</p>	            		
          </span>	        
      </section>
    </div>   
  );
}



function HomeChart() {
  const [reveneuData, setReveneuData] = useState({
    labels: [],
    datasets: [],
  });

  useEffect(() => {
    getReceita().then((res) => {
      const labels = res.carts.map((cart) => {
        return `User-${cart.userId}`;
      });
      const data = res.carts.map((cart) => {
        return cart.discountedTotal;
      });

      const dataSource = {
        labels,
        datasets: [
          {
            label: "Receita",
            data: data,
            backgroundColor: "rgba(50, 100, 255, 1)",
          },
        ],
      };

      setReveneuData(dataSource);
    });
  }, []);

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: "bottom",
      },
      title: {
        display: true,
        text: "Receita por Pedidos",
      },
    },
  };

  //Teste
  const [data, setData] = useState({
    labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho'],
    datasets: [
      {
        label: 'Vendas',
        data: [12, 19, 3, 5, 2, 3, 8],
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1,
      },
    ],
  });

  const lineOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Vendas Mensais',
      },
    },
    scales: {
      x: {
        title: {
          display: true,
          text: 'Mês',
        },
      },
      y: {
        title: {
          display: true,
          text: 'Vendas',
        },
        suggestedMax: 20,
      },
    },
  };
  

  return (
    <div style={{ display: 'flex', flexDirection: 'row' }}>
      <Card style={{ width: 450, height: 250 }}>
        <Bar options={options} data={reveneuData} />
      </Card>
      <Card style={{ width: 500, height: 250 }}>
        <Line options={lineOptions} data={data} />
      </Card>     
    </div>

    
  );
}

export default Home;
