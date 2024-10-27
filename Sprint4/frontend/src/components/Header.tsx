import Link from "next/link";
import portoLogo from "@/imagem/logo_porto.webp"
import Image from "next/image";

export default function Header() {
  return(
    <header className="border-b-2 p-2">
         <nav>
            <ul className="flex justify-between items-center p-4">
                <div className="flex justify-between items-center w-1/2">
                    <li className="w-2/6">
                        <Image className="w-3/4" src={portoLogo} alt="logo da porto" />
                    </li>
                    <li className="uppercase hover:text-blue-700">
                        <Link className="header__link logo" href="#introducao">Introdução</Link>
                    </li>
                    <li className="uppercase hover:text-blue-700"> 
                        <Link className="header__link header__paginas" href="#diferenciais">
                            Diferenciais
                        </Link>
                    </li>
                    <li className="uppercase hover:text-blue-700">
                        <Link className="header__link header__paginas" href="#sobre_nos">
                            Sobre nos
                        </Link>
                    </li>
                </div>
                <li><Link className="border-2 p-4 border-blue-600 uppercase font-semibold hover:bg-blue-400" href="/login">Area do Cliente</Link></li>
            </ul>
        </nav>
    </header>
  )
}