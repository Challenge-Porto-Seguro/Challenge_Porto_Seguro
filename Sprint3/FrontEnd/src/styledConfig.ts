import styled from "styled-components"

export const HeaderStyle = styled.header`
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

export const ErrorStyle = styled.main`
    margin: 1em;
    text-align: center;

    article {
        text-align: center;
        border: 5px solid #8A8888;
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

    section {
        display: flex;
        border-bottom: 2px solid #8A8888;
        padding: 1em 0;
        gap: 20px;
    }

    .principal__logo{
        width: 25%;
    }

    .principal__titulo {
        margin-left: 20px;
        text-transform: uppercase;
        font-size: 3rem;
    }

    .principal__imagem {
        width: 35%;
        border-radius: 50px;
    }

`