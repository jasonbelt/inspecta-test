# Switch Docker Virtual Machine Options to Cocker VMM on Apple Silicon
#
# docker build -t jasonbelt/microkit_domain_scheduling:latest .
# docker login
# docker push jasonbelt/microkit_domain_scheduling:latest

FROM --platform=linux/amd64 ubuntu:24.04

ENV USER=microkit

RUN apt-get update && \
    apt install -y software-properties-common && \
    add-apt-repository ppa:deadsnakes/ppa && \
    apt install -y \
      curl wget emacs git sudo \
      gcc-riscv64-unknown-elf \
      cmake pandoc device-tree-compiler ninja-build \
      texlive-latex-base texlive-latex-recommended \
      texlive-fonts-recommended texlive-fonts-extra \
      libxml2-utils \
      python3.9 python3-pip python3.9-venv \
      qemu-system-arm qemu-system-misc \
      clang lld \
      libssl-dev pkg-config netcat-openbsd && \
    useradd -m -s /bin/bash -N ${USER} && \
    usermod -s /bin/bash ${USER} && \
    echo "${USER} ALL=(ALL) NOPASSWD: ALL" > /etc/sudoers && \
    chmod 0440 /etc/sudoers && \
    chmod g+w /etc/passwd

USER ${USER}
ENV HOME=/home/microkit
WORKDIR ${HOME}

SHELL ["/bin/bash", "-c"]

# don't use cache on next step if any of the repos have a new commit
ADD "https://api.github.com/repos/sel4/sel4/pulls/1308/commits?per_page=1" latest_sel4_commit
ADD "https://api.github.com/repos/sel4/microkit/pulls/175/commits?per_page=1" latest_microkit_commit
ADD "https://api.github.com/repos/ku-sldg/am-cakeml/commits/resolute?per_page=1" latest_am-cakeml_commit
ADD "https://api.github.com/repos/ku-sldg/asp-libs/commits/resolute?per_page=1" latest_asp-libs_commit

RUN rm -rf latest_am-cakeml_commit latest_asp-libs_commit latest_sel4_commit latest_microkit_commit && \
    echo "************************************************" && \
    echo "* Installing Rust                              *" && \
    echo "************************************************" && \
    wget -O rustup-init.sh https://sh.rustup.rs && sh ${HOME}/rustup-init.sh -y && rm ${HOME}/rustup-init.sh && . ${HOME}/.cargo/env && \
    rustup target add x86_64-unknown-linux-musl && rustup target add aarch64-unknown-linux-musl && \
    echo "************************************************" && \
    echo "* Building Microkit SDK with domain scheduling *" && \
    echo "************************************************" && \
    wget -O aarch64-toolchain.tar.gz https://sel4-toolchains.s3.us-east-2.amazonaws.com/arm-gnu-toolchain-12.2.rel1-x86_64-aarch64-none-elf.tar.xz%3Frev%3D28d5199f6db34e5980aae1062e5a6703%26hash%3DF6F5604BC1A2BBAAEAC4F6E98D8DC35B && \
    tar xf aarch64-toolchain.tar.gz && rm ${HOME}/aarch64-toolchain.tar.gz && \
    echo "export PATH=${HOME}/arm-gnu-toolchain-12.2.rel1-x86_64-aarch64-none-elf/bin:${PATH}:." >> ${HOME}/.bashrc && \
    export PATH=${HOME}/arm-gnu-toolchain-12.2.rel1-x86_64-aarch64-none-elf/bin:${PATH}:. && \
    git clone --branch microkit https://github.com/seL4/seL4.git && \
    cd seL4 && git fetch origin pull/1308/head:jarcher && git pull && git checkout jarcher && cd ${HOME} && \
    git clone https://github.com/seL4/microkit.git && \
    cd microkit/ && git fetch origin pull/175/head:jarcher && git pull && git checkout jarcher && \
    export SDK_VERSION=$(./ci/dev_version.sh) && \
    python3.9 -m venv pyenv && \
    ./pyenv/bin/pip install --upgrade pip setuptools wheel && \
    ./pyenv/bin/pip install -r requirements.txt && \
    ./pyenv/bin/python build_sdk.py --experimental-domain-support --sel4=$HOME/seL4 --version $SDK_VERSION && \
    microkit=$(find ~/microkit/release/ -type d -name microkit-sdk*) && \
    echo "export MICROKIT_SDK=${microkit}" >> ${HOME}/.bashrc && \
    echo "export MICROKIT_BOARD=qemu_virt_aarch64" >> ${HOME}/.bashrc && \
    mkdir -p ${HOME}/provers && cd ${HOME}/provers && \
    echo "************************************************" && \
    echo "* Add Sireum install script                    *" && \
    echo "************************************************" && \
    mkdir -p ${HOME}/bin && echo "mkdir -p ${HOME}/provers/Sireum/bin && wget https://raw.githubusercontent.com/sireum/kekinian/refs/heads/master/bin/init.sh -O ${HOME}/provers/Sireum/bin/init.sh && bash ${HOME}/provers/Sireum/bin/init.sh" >> ${HOME}/bin/install-sireum.sh && chmod 700 ${HOME}/bin/install-sireum.sh && \
    echo "export SIREUM_HOME=${HOME}/provers/Sireum" >> ${HOME}/.bashrc && \    
    echo "************************************************" && \
    echo "* Building KU tools                            *" && \
    echo "************************************************" && \
    export PKG_CONFIG_PATH=/usr/lib/x86_64-linux-gnu/pkgconfig/ && \    
    wget https://github.com/CakeML/cakeml/releases/download/v2747/cake-x64-64.tar.gz && tar xzf cake-x64-64.tar.gz && \
    cd cake-x64-64 && make cake && sudo cp cake /usr/bin && cd ${HOME}/provers && rm -rf ${HOME}/provers/cake-x64-64* &&\
    git clone -b resolute https://github.com/ku-sldg/am-cakeml.git && mkdir -p am-cakeml/build && cd am-cakeml/build && \
    cmake .. && make && cd ${HOME}/provers && \
    git clone -b resolute https://github.com/ku-sldg/asp-libs.git && cd asp-libs && make && \
    echo "export ASP_BIN=${HOME}/provers/asp-libs/bin" >> ${HOME}/.bashrc && \
    echo "export DEMO_ROOT=${HOME}/provers" >> ${HOME}/.bashrc && \
    echo "alias env='env | sort'" >> ${HOME}/.bash_aliases && \
    echo "alias dir='ls -lFGa'" >> ${HOME}/.bash_aliases && \
    echo "alias ..='cd ..'" >> ${HOME}/.bash_aliases && \
    sudo apt purge --auto-remove -y \
      pandoc texlive-latex-base texlive-latex-recommended \
      texlive-fonts-recommended texlive-fonts-extra libxml2-utils

