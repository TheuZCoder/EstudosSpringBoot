# Use a imagem base do Node.js com a versão desejada
FROM node:14

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o package.json e o package-lock.json para o diretório de trabalho
COPY package*.json ./

# Instale as dependências do Node.js
RUN npm install

# Copie o restante dos arquivos do projeto para o diretório de trabalho
COPY . .

# Exponha a porta em que sua aplicação Node.js estará ouvindo (substitua PORT pelo número da porta)
EXPOSE PORT

# Comando para iniciar sua aplicação quando o contêiner for executado
CMD [ "node", "index.js" ]
