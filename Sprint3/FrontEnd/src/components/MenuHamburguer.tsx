import { Link } from "react-router-dom";
import { MenuHamburguerStyle } from "../styledConfig";

export default function MenuHamburguer() {

    return (
        <MenuHamburguerStyle className="menu">
            <input type="checkbox" className="menu-faketrigger" />
            <div className="menu-lines">
                <span></span>
                <span></span>
                <span></span>
            </div>
            <ul>
                <li><Link to="/chatbot">ChatBot</Link></li>
                <li><Link to="/colaboradores">Colaboradores</Link></li>
            </ul>
        </MenuHamburguerStyle>
    )
    

}