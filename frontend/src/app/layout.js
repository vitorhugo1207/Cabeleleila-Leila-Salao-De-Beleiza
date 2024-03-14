import { Inter } from "next/font/google";
import { PrimeReactProvider } from 'primereact/api';
import "./globals.css";
import dynamic from 'next/dynamic';

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "Login - Cabeleleila Leila",
  description: "Login page",
};

export default function RootLayout({ children }) {
    return (
        <html lang="en">
            <body className={inter.className}>{children}</body>
        </html>
    );
}
