import styled from "styled-components"
import "./App.css"

export const AppStyle = styled.div`
    display: flex;
    flex-direction: column;
    flex-grow: 1;
`

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

    .header__botao:hover {
        background-color: var(--azul);
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
    background-color: var(--azul);
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

export const SetaVoltarStyle = styled.div`
    .link__imagem {
        width: 5%;
        margin-bottom: 1em;
    }
`

export const ColaboradoresPageStyle = styled.main`
    margin: 1em;

    

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

export const LoginPageStyle = styled.main`
    margin: 1em;

    article {
        width: 50%;
    }

    .container {
        display: flex;
        justify-content: space-between;
        padding: 2em;
    }

    .container__titulos--titulo {
        font-size: 5rem;
    }
    .container__titulos--subtitulo {
        font-size: 2rem;
    }

    .nome__input {
        font-size: 1.5rem;
        margin: 1em 0 0.6em 10px;
    }

    .input {
        width: 80%;
        padding: 1.5em 1em;
        border-radius: 50px;
        background-color: var(--cinza-claro);
        border: none;
    }

    .input::placeholder {
        color: var(--preto);
    }

    .esqueceu__senha {
        display: flex;
        justify-content: end;
        width: 80%;
        margin: 1em 0;
        color: var(--azul);
    }

    .div__botao {
        width: 80%;
        text-align: center;
    }

    a {
        margin-top: 50px;
        color: var(--azul);
    }

    .btn {
        padding: 1em 8em;
        font-weight: 700;
        background-color: var(--azul);
        border: none;
        color: var(--branco);
    }

    .btn:hover {
        background-color: var(--azul-claro);
        color: var(--preto);
    }

`
export const CadastroPageStyle = styled.main`
    display: flex;
    flex-direction: column;
    align-items: center;
    flex-grow: 1;
    justify-content: center;
    padding: 3em;


    h1 {
        color: var(--azul-claro);
        border-bottom: 1px solid var(--cinza);
        width: 50%;
        text-align: center;
        padding: 0.5em;
    }
`

export const FormularioCadastroStyle = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    width: 60%;
    margin: 2em;
    border: 0.7px solid var(--preto);
    padding: 1em;
    border-radius: 20px;
    background-color: #F5F7F8;

    .endereco_container {
        display: flex;
        width: 60%;
        gap: 10px;
    }

    .botao_cadastro {
        margin-top: 10px;
        padding: 0.8em 5em;
        background-color: var(--branco);
        border: 1px solid var(--preto);
    }

    .botao_cadastro:hover {
        background-color: var(--azul);
        transition: 1s;
    }

    .rua {
        width: 85%;
    }

    .numero {
        width: 15%;
    }


`

export const FormularioCadastroSectio = styled.section`

    display: flex;
    flex-direction: column;
    width: 100%;
    align-items: center;

    .campo {
            margin-bottom: 10px;
        }

    .input {
        margin-bottom: 10px;
        padding: 0.5em 0.3em;
        border-radius: 10px;
        width: 60%;
        color: var(--preto);
    }

    .input::placeholder {
        text-align: center;
        color: var(--preto);
    }

    #rua {
        width: 100%;
    }

    #numero {
        width: 100%;
    }

   

`

export const FormularioCadastroVeiculo = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    width: 60%;
    margin: 2em;
    border: 0.7px solid var(--preto);
    padding: 1em;
    border-radius: 20px;
    background-color: #F5F7F8;

    .botao_cadastro {
        margin-top: 10px;
        padding: 0.8em 5em;
        background-color: var(--branco);
        border: 1px solid var(--preto);
    }

    .botao_cadastro:hover {
        background-color: var(--azul);
        transition: 1s;
    }
`
