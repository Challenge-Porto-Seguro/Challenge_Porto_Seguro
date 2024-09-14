import { ColaboradorStyle } from "../styledConfig";
import { ListaColaboradores } from "../types";

export default function Colaborador({imagem, nome, rm, link_github} : ListaColaboradores){
    return (
        <ColaboradorStyle >
            <img className="colaborador__imagem" src={imagem} alt="" />
            <h2 className="colaborador__nomerm">{nome} RM:{rm}</h2>
            <a className="colaborador__github" href={link_github} target="_blank">Link Github</a>
        </ColaboradorStyle>
    )
}