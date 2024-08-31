import logo_porto from "./../assets/logo_porto.webp"
import colaboradores from "./../assets/colaboradores.png"
import chatbot from "./../assets/iconeChatBot.png"
import styled from "styled-components"
import { Link } from "react-router-dom"

const HeaderStyle = styled.header`
    border-bottom: 2px solid #8A8888;
    padding: 0.2em 0;
    color: var(--primary-color);

    .header__lista{
        display: flex;
        align-items: center;
        gap: 50px;
    }

    .header__lista--item {
        width: 25%;
        text-align: center;
        list-style: none;
    }

    .header__link{
        text-decoration: none;
        color: var(--primary-color);
    }


    .header__logo {
        width: 70%;
    }   

    .header__paginas--img {
        width: 20%;
    }

    .header__botao {
        border: 2px solid black;
        text-align: center;
        padding: 1em 2em;
        width: 15em;
        margin: 0 1em;
    }

`

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