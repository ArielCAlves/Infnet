import { Typography } from "antd";

function AppFooter(){
    return(
        <div className="AppFooter">            
            <Typography.Link href="https://www.google.com" target="_blank">Termos de Uso</Typography.Link>
            <p className="dark:text-gray-200 text-gray-700 text-center m-20">
                © Projeto de Bloco - Ariel Carvalho Alves
            </p> 
            <Typography.Link href="https://www.google.com" target="_blank">Política de Privacidade</Typography.Link>
            
        </div>        
            
    );
}

export default AppFooter;