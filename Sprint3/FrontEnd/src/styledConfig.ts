import styled from "styled-components"
import "./App.css"

export const HeaderStyle = styled.header`
    border-bottom: 2px solid var(--cinza);
    padding: 0.2em 0;
    color: var(--peto);

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
        color: var(--preto);
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
        font-weight: 700;
    }

`

export const ErrorStyle = styled.main`
    margin: 1em;
    text-align: center;

    article {
        text-align: center;
        border: 5px solid var(--cinza);
        padding: 1em;
    }

    h3 {
        margin-top: 5em;
    }

    a{
        text-decoration: none;
        color: darkblue;
    }

    a:hover {
        color: blue;
    }
    
` 

export const HomeStyle = styled.main`

    .home__introducao {
        display: flex;
        border-bottom: 2px solid var(--cinza);
        padding: 1em 2em;
        gap: 15px;
    }

    article {
        width: 50%;
    }

    .principal__logo{
        width: 40%;
    }

    .principal__titulo {
        margin-left: 20px;
        text-transform: uppercase;
        font-size: 2rem;
    }

    .principal__imagem {
        width: 45%;
        border-radius: 50px;
    }

    .home__sobre-nos{
        padding: 0 1em;
    }

    .container {
        text-align: center;
    }

    .home__sobre-nos--titulo {
        margin: 20px 0;
        font-size: 2.5rem;
    }

    .home__sobre-nos--paragrafo {
        margin: 10px 0;
        text-indent: 10px;
        font-size: 1.3rem;
        font-family: var(--fonte-secundaria);
        word-spacing: 5px;
    }

`

export const FooterStyle = styled.footer`
    display: flex;
    justify-content: space-between;
    background-color: #007FFF;
    padding: 1em 1.3em;
    color: var(--branco);
    text-transform: uppercase;
    font-weight: 700;

`

export const ChatBotPageStyle = styled.main`
    display: flex;
    flex-direction: column;
    text-align: center;
    padding: 1em;
    gap: 40em;

    .titulo {
        font-family: var(--fonte-terciaria);
        letter-spacing: 6px;
    }

    .container{
        display: flex;
        justify-content: center;
    }

    .container__input{
        width: 80%;
        border-radius: 50px;
    }

    .container__input::placeholder{
        text-align: center;
    }

    .container__imagem {
        width: 100%;
    }

    .container__btn {
        text-decoration: none;
        background: none;
        border: none;
        width: 3%;
    }

`

export const ColaboradoresPageStyle = styled.main`
    margin: 1em;

    .link__imagem {
        width: 5%;
        margin-bottom: 1em;
    }

    .colaboradores {
        display: flex;
        justify-content: center;
        gap: 20px;
        margin-top: 2em 0;
    }

    .colaborador {
        display: flex;
        flex-direction: column;
        gap: 10px;
        align-items: center;
        text-align: center;
        border: 2px solid var(--preto);
        padding: 1em;
        border-radius: 20px;
    }

    .colaborador__imagem{
        width: 50%;
        border-radius: 50%;
    }

    .colaborador__nomerm {
        font-family: var(--fonte-secundaria);
        font-size: 2rem;
    }

    .colaborador__github {
        text-decoration: none;
        font-size: 1.3rem;
        color: var(--preto);
    }

    .colaborador__github:hover {
        color: #007FFF;
    }

    .links {
        margin: 1.5em 0;
        display: flex;
        flex-direction: column;
        gap: 50px;
    }

    .links__link {
        color: var(--preto);
        font-size: 2rem;
        text-transform: uppercase;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 10px;
        margin: 0 auto;
    }

    .links__link:hover {
        color: var(--azul);
    }

    .links__link--imagem {
        width: 5%;
    }

`