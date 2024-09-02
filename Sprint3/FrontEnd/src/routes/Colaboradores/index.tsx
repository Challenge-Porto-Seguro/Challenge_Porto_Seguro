import setaVoltar from "../../assets/icone_voltar.svg"
import colaboradorPedro from "../../assets/fotos_contribuidores/foto_pedro.jpg"
import ColaboradorThiago from "../../assets/fotos_contribuidores/foto_thiago.jpg"
import IconeGitHub from "../../assets/github.png"
import IconeFigma from "../../assets/iconeFigma.webp"
import Colaborador from "../../components/Colaborador";
import { ListaColaboradores } from "../../types";
import { ColaboradoresPageStyle } from "../../styledConfig";
import { Link } from "react-router-dom";

export default function Colaboradores() {
    document.title = "Colaboradores"

    const colaboradores:ListaColaboradores[] = [
        {imagem: colaboradorPedro, nome: "Pedro Henrique dos Santos", rm: 559064, link_github: "https://github.com/Pedro-Henrique3216"},
        {imagem: ColaboradorThiago, nome: "Thiago Thomaz", rm: 557992, link_github: "https://github.com/ThiagoThmaz"}
    ];

    return(
        <ColaboradoresPageStyle>
            <Link to="/" className="link"><img className="link__imagem" src={setaVoltar} alt="seta de voltar" /></Link>
            <section className="colaboradores">
                {   
                    colaboradores.map((colab, index) => (
                        <Colaborador index={index} imagem={colab.imagem} nome={colab.nome} rm={colab.rm} link_github={colab.link_github} />
                    ))
                }
            </section>
            <section className="links">
                <a className="links__link" href="https://github.com/Challenge-Porto-Seguro/Challenge_Porto_Seguro" target="_blank"><img className="links__link--imagem" src={IconeGitHub} alt="icone do github" />Link do Projeto no GitHub</a>
                <a className="links__link" href="https://www.figma.com/team_invite/redeem/853grbCAf7qkMOEln39byr" target="_blank"><img className="links__link--imagem" src={IconeFigma} alt="icone do figma" />Link do Projeto no Figma</a>
            </section>
            
        </ColaboradoresPageStyle>
    )
}