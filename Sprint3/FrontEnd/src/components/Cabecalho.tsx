import logo_porto from "./../assets/logo_porto.webp"
import colaboradores from "./../assets/colaboradores.png"
import chatbot from "./../assets/iconeChatBot.png"
import { HeaderStyle } from "../styledConfig"
import { Link } from "react-router-dom"

export default function Cabecalho() {

    return (
        <HeaderStyle>
            <nav>
                <ul className="header__lista">
                    <li className="header__lista--item">
                        <Link className="header__link logo" to="/"><img className="header__logo" src={logo_porto} alt="Logo da Porto" /></Link>
                    </li>
                    <li className="header__lista--item"> 
                        <Link className="header__link header__paginas" to="/chatbot">
                            <img className="header__paginas--img" src={chatbot} alt="logo do chatbot" />
                            <h3>ChatBot</h3>
                        </Link>
                    </li>
                    <li className="header__lista--item">
                        <Link className="header__link header__paginas" to="colaboradores">
                            <img className="header__paginas--img" src={colaboradores} alt="logo de colaboradores" />
                            <h3>Colaboradores</h3>
                        </Link>
                    </li>
                    <li className="header__lista--item"><Link className="header__botao header__link" to="/login">Area do Cliente</Link></li>
                </ul>
            </nav>
        </HeaderStyle>
    )
}