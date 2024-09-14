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
        margin: 0 1em;
        font-weight: 700;
    }

    .header__botao:hover {
        background-color: var(--azul);
    }

    .menu {
        display: none;
    }

    @media screen and (max-width: 1024px) {
        .header__botao {
            padding: 1em 1em;
        }
        
    }

    @media screen and (max-width: 900px){
        .header__logo {
            width: 80%;
        }

        .header__paginas--img {
            width: 30%;
        }   

        .header__botao{
            font-size: 0.8rem;
        }
    }

    @media screen and (max-width: 750px){

        justify-content: space-between;

        .header__lista--item:nth-child(1) {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .menu{
            display: block;
            margin: 0 0 40px 20px ;
        }
        
        .header__lista--item:nth-child(2), .header__lista--item:nth-child(3){
            display: none;
        }

        .header__lista--item {
            width: 50%;
        }

        .header__botao{
            font-size: 1rem;
        }
    }

    @media screen and (max-width: 421px) {
        .header__lista {
            gap: 20px;
        }
        
        .header__logo {
            width: 100%;
            margin-left: 15px;
        }
        .header__botao{
            font-size: 0.8rem;
        }
    }

    @media screen and (max-width: 320px) {
        .menu {
            margin-bottom: 30px;
        }

        .header__botao{
            font-size: 0.7rem;
            padding: 1em 0.7em;
        }
        
    }


`

export const MenuHamburguerStyle = styled.nav`

    .menu-lines, input{
        display: none;
    }
        
    .menu-faketrigger{
        position: absolute;
        z-index: 1000;
        width: 35px;
        height: 35px;
        opacity: 0;
        cursor:pointer
    }

    .menu-lines{
        display: block;
        position: absolute;
        z-index: 999;
        width: 35px;
        height: 35px;
    }

    input{
        display: block;
    }

    .menu-lines span{
        display:block;
        width: 35px;
        height: 5px;
        margin-bottom: 10px;
        background-color: #cdcdcd;
        border-radius: 3px;
        transition: all ease .2s;
    }


    .menu-faketrigger:checked ~ .menu-lines span{
        background-color: #222;
            
    }
    .menu-faketrigger:checked ~ .menu-lines span:nth-child(1){
        transform-origin: 0% 0%;
        transform: rotate(45deg) scaleX(1.25);

    }
    .menu-faketrigger:checked ~ .menu-lines span:nth-child(2){
        opacity: 0;

    }
    .menu-faketrigger:checked ~ .menu-lines span:nth-child(3){
        transform-origin: 0% 100%;
        transform: rotate(-45deg) scaleX(1.25);

    }

    ul{
        display: block;
        position: absolute;
        z-index: 998;
        left: 0;
        top: 0;
        width: 250px;
        padding-top: 80px;
        background-color: var(--cinza-claro);
        margin-left: -300px;
        transition: all ease .2s;
    }

    .menu-faketrigger:checked ~ ul{
        margin-left: 0;
    }

    ul li{
        padding: 5px;
    }

    ul li a{    
        color: var(--preto);
        font-size: 22px;
        margin: 0;
        text-decoration: none;
        display: block;
        padding: 10px 20px;
        transition: all ease .3s;
    }

    ul li a:hover {
        background-color: var(--azul-claro);
        color: var(--branco);

    }

    @media screen and (max-width: 375px){
        ul {
            width: 200px;
        }
    }
    
    @media screen and (max-width: 320px){
        .menu-faketrigge {
            width: 30px;
            height: 20px;
        }
        
        .menu-lines {
            height: 30px;
            width: 20px;
        }

        .menu-lines span {
            width: 30px;
            margin-bottom: 8px;
        }
    }
    
`

export const ErrorStyle = styled.main`
    margin: 1em;
    text-align: center;
    flex-grow: 1;

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
    flex-grow: 1;

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

    @media screen and (max-width: 900px) {
        .principal__logo {
            width: 50%;
        }
        .principal__titulo{
            font-size: 1.8rem;
        }

        .home__sobre-nos--titulo {
            font-size: 2rem;
        }

        .home__sobre-nos--paragrafo {
            font-size: 1rem;
        }
        
    }

    @media screen and (max-width: 640px){
        
        .home__introducao {
            flex-direction: column;
            text-align: center;
        }

        .principal__logo {
            width: 35%;
        }

        .principal__titulo{
            font-size: 1.5rem;
        }

        article  {
            width: 100%;
        }

        .principal__imagem {
            width: 100%;
        }

        .principal__titulo {
            margin: 0;
        }
    }

    @media screen and (max-width: 471px) {

        .home__introducao {
            padding: 1em 1em;
            align-items: center;
        }

        .principal__titulo{
            font-size: 1.2rem;
        }

        .principal__imagem {
            width: 80%;
        }
    }

    @media screen and (max-width: 330px){
        .home__sobre-nos--titulo {
            font-size: 1.5rem;
        }

        .home__sobre-nos--paragrafo {
            font-size: 0.8rem;
        }     
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

    @media screen and (max-width: 640px) {
        h3 {
            font-size: 1rem;
        }
    }

    @media screen and (max-width: 421px) {
        h3 {
            font-size: 0.8rem;
        }
    }

    @media screen and (max-width: 320px){
        flex-direction: column;
        align-items: center;
        gap: 10px;
    }

`

export const ChatBotPageStyle = styled.main`
    display: flex;
    text-align: center;
    flex-grow: 1;
    padding: 1em;
    gap: 10px;

    .automoveis, .chatbot {
        width: 50%;
        display: flex;
        flex-direction: column;
        gap: 20px;
    }

    .automoveis {
        align-items: center;
    }

    .titulo__automoveis {
        color: var(--azul-claro);
        font-size: 2rem;
    }

    a {
        color: var(--preto);
        border: 1px solid var(--preto);
        text-decoration: none;
        padding: 0.7em 1.5em;
        border-radius: 10px;
    }

    a:hover {
        background-color: var(--azul-claro);
        transition: 1s;
    }


    .chatbot {
        justify-content: space-between;
        align-items: center;
    }


    .titulo {
        font-size: 2.5rem;
        font-family: var(--fonte-terciaria);
        letter-spacing: 6px;
    }

    .container{
        display: flex;
        justify-content: center;
    }

    .container__input{
        padding: 1em;
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
        width: 7%;
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

export const AutomoveisComponentSyle = styled.section`
    display: flex;
    gap: 20px;
    border: 1px solid var(--preto);
    border-radius: 20px;
    width: 50%;
    background-color: #F5F7F8;
    padding: 0.5em 1em;
    justify-content: center;

`
