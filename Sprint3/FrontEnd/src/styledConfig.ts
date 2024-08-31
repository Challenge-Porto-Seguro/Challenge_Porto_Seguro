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
    margin: 1em;

    .home__introducao {
        display: flex;
        border-bottom: 2px solid var(--cinza);
        padding: 1em 0;
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
        width: 50%;
        border-radius: 50px;
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